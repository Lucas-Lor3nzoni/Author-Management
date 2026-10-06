package br.com.apiserver.author.controller.dto;

import java.time.LocalDate;

public record AuthorResponse(
        Long id, String name, String email, LocalDate birthDate, String countryCode, String cpf

) {
}
