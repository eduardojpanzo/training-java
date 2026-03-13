package dev.eduardojpanzo.user_management.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Email já registado: " + email);
    }
}
