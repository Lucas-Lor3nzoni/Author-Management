package br.com.apiserver.authentication.controller.dto;

import br.com.apiserver.authentication.model.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record AdminRegisterRequest(

        @NotBlank(message = "Administrator name cannot be null or empty")
        String name,

        @NotBlank(message = "Administrator email cannot be null or empty")
        @Email(message = "Administrator email must be valid")
        String email,

        @NotBlank(message = "Administrator password cannot be null or empty")
        @Size(min = 6, message = "Administrator password must have at least 6 characters")
        String password,

        @NotNull(message = "Administrator roles cannot be null")
        Set<Roles> roles
        
) { }