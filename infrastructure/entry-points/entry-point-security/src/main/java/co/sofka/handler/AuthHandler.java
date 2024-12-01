package co.sofka.handler;

import co.sofka.*;
import co.sofka.data.UserIdDto;
import co.sofka.appservice.jwt.AuthenticateUseCase;
import co.sofka.appservice.jwt.GetUserByEmailUseCase;
import co.sofka.appservice.jwt.RegisterUseCase;
import org.springframework.stereotype.Component;

@Component
public class AuthHandler {

    private final AuthenticateUseCase authenticateUseCase;
    private final RegisterUseCase registerUseCase;
    private final GetUserByEmailUseCase getUserByEmailUseCase;


    public AuthHandler(AuthenticateUseCase authenticateUseCase, RegisterUseCase registerUseCase, GetUserByEmailUseCase getUserByEmailUseCase) {
        this.authenticateUseCase = authenticateUseCase;
        this.registerUseCase = registerUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        return authenticateUseCase.apply(authenticationRequest);
    }

    public AuthenticationResponse register(UserRequest userRequest) {
        return registerUseCase.apply(userRequest);
    }

    public UserRequest getUserByEmail(UserIdDto userIdDto) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(userIdDto.getId());
        return getUserByEmailUseCase.getUserByEmail(authenticationRequest);
    }

}
