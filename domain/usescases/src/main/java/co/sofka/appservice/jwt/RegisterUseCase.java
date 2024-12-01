package co.sofka.appservice.jwt;

import co.sofka.AuthenticationResponse;
import co.sofka.UserRequest;
import co.sofka.jwt.AuthRepository;


public class RegisterUseCase {

    private final AuthRepository authRepository;

    public RegisterUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public AuthenticationResponse apply(UserRequest userRequest) {
        return authRepository.register(userRequest);
    }
}
