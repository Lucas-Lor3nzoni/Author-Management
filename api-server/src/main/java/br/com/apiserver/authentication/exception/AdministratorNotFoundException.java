package br.com.apiserver.authentication.exception;

public class AdministratorNotFoundException extends RuntimeException {
    public AdministratorNotFoundException(String message) {
        super(message);
    }
}
