package br.com.apiserver.authentication.exception;

public class AdministratorAlreadyExistsException extends RuntimeException {
    public AdministratorAlreadyExistsException(String message) {
        super(message);
    }
}
