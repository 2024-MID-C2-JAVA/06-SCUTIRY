package co.sofka;

import co.sofka.in.RegisterUseCase;
import co.sofka.out.AuthRepository;


public class RegisterUseCaseImpl implements RegisterUseCase {

    private final AuthRepository authRepository;

    public RegisterUseCaseImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public AuthenticationResponse apply(RegisterRequest registerRequest) {
        return authRepository.register(registerRequest);
    }
}
