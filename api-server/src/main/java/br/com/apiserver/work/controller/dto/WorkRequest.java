package br.com.apiserver.work.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

public record WorkRequest(
        @NotBlank(message = "Title cannot be empty!")
        String title,

        @NotBlank(message = "Description cannot be empty!")
        @Size(max = 240, message = "Description cannot be longer than 240 characters!")
        String description,

        Instant releaseDate,

        Instant exhibitionDate,

        @NotNull(message = "Author IDs cannot be null!")
        List<Long> authorIds
) {
}
