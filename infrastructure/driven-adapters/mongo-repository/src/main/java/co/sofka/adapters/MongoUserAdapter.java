package co.sofka.adapters;

import co.sofka.AuthenticationRequest;
import co.sofka.AuthenticationResponse;
import co.sofka.UserRequest;
import co.sofka.config.JwtService;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Optional<UserDocument> user = Optional.ofNullable(mongoTemplate.findOne(query, UserDocument.class));

        if (user.isEmpty()) {
            UserDocument userDocument = new UserDocument.Builder()
                    .setFirstName(userRequest.getFirstname())
                    .setLastName(userRequest.getLastname())
                    .setEmail(userRequest.getEmail())
                    .setPassword(passwordEncoder.encode(userRequest.getPassword()))
                    .setRole(userRequest.getRole())
                    .build();


            mongoTemplate.save(userDocument);

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

        List<String> roles = List.of(user.getRole().toString());


        String jwtToken = jwtService.generateToken(user, Map.of("roles", String.join(",", roles)));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .build();
    }





    @Override
    public UserRequest getUserByEmailUseCase(AuthenticationRequest authenticationRequest) {
        Optional<UserDocument> optionalUser = Optional.ofNullable(mongoTemplate.findById(authenticationRequest.getEmail(), UserDocument.class));

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with id: " + authenticationRequest.getEmail());
        }

        UserDocument user = optionalUser.get();

        return UserRequest.builder()
                .id(user.getId())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .email(user.getEmail())
                .build();
    }


}
