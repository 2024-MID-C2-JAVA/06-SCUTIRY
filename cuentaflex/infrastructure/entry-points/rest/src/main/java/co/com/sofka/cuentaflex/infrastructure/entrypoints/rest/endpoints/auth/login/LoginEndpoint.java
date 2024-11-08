package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.auth.login;

import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.constants.AuthEndpoinstConstants;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinRequest;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthEndpoinstConstants.LOGIN_ENDPOINT)
@Tag(name = "Auth")
public final class LoginEndpoint {
    private final LoginService loginService;

    public LoginEndpoint(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<DinResponse<LoginResponseDto>> login(
            @RequestBody DinRequest<LoginRequestDto> request
    ) {
        return this.loginService.login(request);
    }
}