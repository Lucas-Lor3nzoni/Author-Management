package br.com.apiserver.author.exception;

public class AuthorBusinessException extends RuntimeException {
    public AuthorBusinessException(String message) {
        super(message);
    }
}
