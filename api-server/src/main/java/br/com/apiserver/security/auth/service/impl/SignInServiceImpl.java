package br.com.apiserver.security.auth.service.impl;

import br.com.apiserver.security.auth.service.SignInService;
import br.com.apiserver.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SignInServiceImpl implements SignInService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String signIn(String email, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        return jwtService.generateToken(authentication.getName());
    }

}