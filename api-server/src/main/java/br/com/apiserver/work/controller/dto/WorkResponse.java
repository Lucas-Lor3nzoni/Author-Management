package br.com.apiserver.work.controller.dto;

import java.time.Instant;

public record WorkResponse(
        Long id, String title, String description, Instant releaseDate, Instant exhibitionDate
) {
}
