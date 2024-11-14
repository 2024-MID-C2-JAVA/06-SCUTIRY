package co.sofka.appservice.jwt;

import co.sofka.AuthenticationRequest;
import co.sofka.AuthenticationResponse;
import co.sofka.jwt.AuthRepository;


public class AuthenticateUseCase {

    private final AuthRepository authRepository;

    public AuthenticateUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public AuthenticationResponse apply(AuthenticationRequest authenticationRequest) {
        return authRepository.authenticate(authenticationRequest);
    }
}
