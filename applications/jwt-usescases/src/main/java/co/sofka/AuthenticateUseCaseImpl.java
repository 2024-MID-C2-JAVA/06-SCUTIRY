package co.sofka;

import co.sofka.in.AuthenticateUseCase;
import co.sofka.out.AuthRepository;


public class AuthenticateUseCaseImpl implements AuthenticateUseCase {

    private final AuthRepository authRepository;

    public AuthenticateUseCaseImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public AuthenticationResponse apply(AuthenticationRequest authenticationRequest) {
        return authRepository.authenticate(authenticationRequest);
    }
}
