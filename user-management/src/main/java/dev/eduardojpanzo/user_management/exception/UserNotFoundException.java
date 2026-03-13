package dev.eduardojpanzo.user_management.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Utilizador não encontrado com id: " + id);
    }
}
