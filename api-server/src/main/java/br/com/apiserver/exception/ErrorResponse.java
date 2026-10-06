package br.com.apiserver.exception;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(

        String message, int statusCode, Instant timestamp, List<String> errors

) {

    public ErrorResponse(String message, int statusCode, Instant timestamp) {
        this(message, statusCode, timestamp, null);
    }

}
