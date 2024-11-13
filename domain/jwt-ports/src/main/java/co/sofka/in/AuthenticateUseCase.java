package co.sofka.in;

import co.sofka.AuthenticationRequest;
import co.sofka.AuthenticationResponse;

public interface AuthenticateUseCase {
    AuthenticationResponse apply(AuthenticationRequest authenticationRequest);
}
