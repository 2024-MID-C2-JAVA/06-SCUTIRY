package co.sofka;

import co.sofka.in.GetUserByEmailUseCase;
import co.sofka.out.AuthRepository;

public class GetUserByEmailUseCaseImpl implements GetUserByEmailUseCase {

    private final AuthRepository authRepository;

    public GetUserByEmailUseCaseImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public RegisterRequest getUserByEmail(AuthenticationRequest request) {
        return authRepository.getUserByEmailUseCase(request);
    }
}
