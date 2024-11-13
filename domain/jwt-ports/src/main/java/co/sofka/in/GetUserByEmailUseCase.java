package co.sofka.in;

import co.sofka.AuthenticationRequest;
import co.sofka.RegisterRequest;

public interface GetUserByEmailUseCase {
    RegisterRequest getUserByEmail(AuthenticationRequest request);
}
