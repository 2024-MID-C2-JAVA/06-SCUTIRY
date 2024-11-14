package co.sofka.appservice.jwt;

import co.sofka.AuthenticationRequest;
import co.sofka.UserRequest;
import co.sofka.jwt.AuthRepository;


public class GetUserByEmailUseCase {

    private final AuthRepository authRepository;

    public GetUserByEmailUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public UserRequest getUserByEmail(AuthenticationRequest request) {
        return authRepository.getUserByEmailUseCase(request);
    }
}
