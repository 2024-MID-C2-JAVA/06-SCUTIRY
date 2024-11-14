package co.sofka.adapters;

import co.sofka.AuthenticationRequest;
import co.sofka.AuthenticationResponse;
import co.sofka.UserRequest;
import co.sofka.config.JwtService;
import co.sofka.data.CustomerDocument;
import co.sofka.data.Roles;
import co.sofka.data.UserDocument;
import co.sofka.jwt.AuthRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class MongoUserAdapter implements AuthRepository {

    private final MongoTemplate mongoTemplate;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public MongoUserAdapter(MongoTemplate mongoTemplate, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.mongoTemplate = mongoTemplate;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResponse register(UserRequest userRequest) {

        Query query = new Query(Criteria.where("email").is(userRequest.getEmail()));
        UserDocument user = mongoTemplate.findOne(query, UserDocument.class);

        if (user == null) {
            user = new UserDocument.Builder()
                    .setFirstName(userRequest.getFirstname())
                    .setLastName(userRequest.getLastname())
                    .setEmail(userRequest.getEmail())
                    .setPassword(passwordEncoder.encode(userRequest.getPassword()))
                    .setRole(Roles.USER)
                    .build();

            CustomerDocument customerDocument = new CustomerDocument();

            customerDocument.setName(userRequest.getFirstname() + " " + userRequest.getLastname());

            user.setCustomer(customerDocument);

            mongoTemplate.save(user);

            return AuthenticationResponse.builder().build();
        }else{
            throw new UsernameNotFoundException("Username already exists, please authenticate");
        }
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Query query = new Query(Criteria.where("email").is(authenticationRequest.getEmail()));
        UserDocument user = mongoTemplate.findOne(query, UserDocument.class);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + authenticationRequest.getEmail());
        }

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials provided");
        }


        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }



    @Override
    public UserRequest getUserByEmailUseCase(AuthenticationRequest authenticationRequest) {
        Query query = new Query(Criteria.where("email").is(authenticationRequest.getEmail()));
        UserDocument user = mongoTemplate.findOne(query, UserDocument.class);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + authenticationRequest.getEmail());
        }

        return UserRequest.builder()
                .id(user.getId())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .email(user.getEmail()).build();
    }

}
