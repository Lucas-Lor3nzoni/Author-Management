package br.com.apiserver.work.exception;

public class WorkNotFoundException extends RuntimeException {
    public WorkNotFoundException(String message) {
        super(message);
    }
}
