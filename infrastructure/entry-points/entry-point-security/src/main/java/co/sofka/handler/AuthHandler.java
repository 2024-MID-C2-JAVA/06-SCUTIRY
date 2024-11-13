package co.sofka.handler;

import co.sofka.*;
import co.sofka.data.UserEmailDto;
import org.springframework.stereotype.Component;

@Component
public class AuthHandler {

    private final AuthenticateUseCaseImpl authenticateUseCase;
    private final RegisterUseCaseImpl registerUseCase;
    private final GetUserByEmailUseCaseImpl getUserByEmailUseCase;


    public AuthHandler(AuthenticateUseCaseImpl authenticateUseCase, RegisterUseCaseImpl registerUseCase, GetUserByEmailUseCaseImpl getUserByEmailUseCase) {
        this.authenticateUseCase = authenticateUseCase;
        this.registerUseCase = registerUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        return authenticateUseCase.apply(authenticationRequest);
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        return registerUseCase.apply(registerRequest);
    }

    public RegisterRequest getUserByEmail(UserEmailDto userEmailDto) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(userEmailDto.getEmail());
        return getUserByEmailUseCase.getUserByEmail(authenticationRequest);
    }

}
