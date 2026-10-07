package br.com.apiserver.security.auth.controller;

import br.com.apiserver.security.auth.controller.dto.SignInRequest;
import br.com.apiserver.security.auth.controller.dto.SignInResponse;
import br.com.apiserver.security.auth.controller.mapper.AuthMapper;
import br.com.apiserver.security.auth.service.SignInService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(
        name = "Authentication",
        description = "Authentication endpoints"
)

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/auth/sign-in")
public class AuthController {

    private final SignInService signInService;
    private final AuthMapper authMapper;

    @Operation(
            summary = "Sign in",
            description = "Authenticates a user using their email and password and returns an access token"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sign in successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest request) {
        var token = signInService.signIn(
                request.email(),
                request.password()
        );

        return ResponseEntity.ok(
                authMapper.toResponse(token)
        );
    }
}
