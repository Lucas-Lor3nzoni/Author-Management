package br.com.apiserver.security.auth.controller;

import br.com.apiserver.security.auth.controller.dto.SignInRequest;
import br.com.apiserver.security.auth.controller.dto.SignInResponse;
import br.com.apiserver.security.auth.controller.mapper.AuthMapper;
import br.com.apiserver.security.auth.service.SignInService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/auth/sign-in")
public class AuthController {

    private final SignInService signInService;
    private final AuthMapper authMapper;

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
