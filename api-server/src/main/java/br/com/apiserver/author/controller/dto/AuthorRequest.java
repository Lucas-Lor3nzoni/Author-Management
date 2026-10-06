package br.com.apiserver.author.controller.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AuthorRequest(

        @NotBlank(message = "Name cannot be empty!")
        @Size(
                min = 3,
                max = 150,
                message = "Name must be between 3 and 150 characters!")
        String name,

        @Email(message = "Email must be valid!")
        String email,

        @NotNull(message = "Birth date cannot be empty!")
        @Past(message = "Birth date must be in the past!")
        LocalDate birthDate,

        @NotBlank(message = "Country code cannot be empty!")
        @Size(
                min = 2,
                max = 2,
                message = "Country code must be exactly 2 characters!")
        String countryCode,

        @Pattern(
                regexp = "\\d{11}",
                message = "CPF must contain exactly 11 digits!"
        )
        String cpf
) {
}
