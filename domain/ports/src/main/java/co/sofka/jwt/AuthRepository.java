package co.sofka.jwt;


import co.sofka.AuthenticationRequest;
import co.sofka.AuthenticationResponse;
import co.sofka.UserRequest;

public interface AuthRepository {
    AuthenticationResponse register(UserRequest userRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    UserRequest getUserByEmailUseCase(AuthenticationRequest authenticationRequest);
}
