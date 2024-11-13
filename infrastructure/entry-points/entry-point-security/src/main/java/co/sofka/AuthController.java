package co.sofka;


import co.sofka.data.UserEmailDto;
import co.sofka.handler.AuthHandler;
import din.DinError;
import din.DinErrorEnum;
import din.RequestMs;
import din.ResponseMs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthHandler authHandler;

    public AuthController(AuthHandler authHandler) {
        this.authHandler = authHandler;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMs<AuthenticationResponse>> register(@RequestBody RequestMs<RegisterRequest> request) {
        return ResponseEntity.ok(new ResponseMs<>(request.getDinHeader(), authHandler.register(request.getDinBody()), new DinError(DinErrorEnum.SUCCESS)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseMs<AuthenticationResponse>> authenticate(
            @RequestBody RequestMs<AuthenticationRequest> request) {

        AuthenticationResponse authResponse = authHandler.authenticate(request.getDinBody());

        return ResponseEntity.ok(new ResponseMs<>(request.getDinHeader(), authResponse, new DinError(DinErrorEnum.SUCCESS)));
    }


    @PostMapping("/getUser")
    public ResponseEntity<ResponseMs<RegisterRequest>> getUser(@RequestBody RequestMs<UserEmailDto> dto) {
        return ResponseEntity.ok(new ResponseMs<>(dto.getDinHeader(),authHandler.getUserByEmail(dto.getDinBody()), new DinError(DinErrorEnum.SUCCESS)));
    }

}
