package co.sofka.in;

import co.sofka.AuthenticationResponse;
import co.sofka.RegisterRequest;

public interface RegisterUseCase {
    AuthenticationResponse apply(RegisterRequest registerRequest);
}
