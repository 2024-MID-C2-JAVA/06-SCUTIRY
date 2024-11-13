package co.sofka.out;

import co.sofka.AuthenticationRequest;
import co.sofka.AuthenticationResponse;
import co.sofka.RegisterRequest;

public interface AuthRepository {
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    RegisterRequest getUserByEmailUseCase(AuthenticationRequest authenticationRequest);

}
