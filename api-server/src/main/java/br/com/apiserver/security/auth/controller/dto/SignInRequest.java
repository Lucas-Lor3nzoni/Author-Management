package br.com.apiserver.security.auth.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest(

        @NotBlank(message = "Email cannot be empty!")
        @Email(message = "Email address must be valid!")
        String email,

        @NotBlank(message = "Password cannot be empty!")
        String password

) { }