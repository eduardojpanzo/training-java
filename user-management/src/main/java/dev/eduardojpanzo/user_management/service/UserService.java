package dev.eduardojpanzo.user_management.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.eduardojpanzo.user_management.dto.UserDTO.CreateUserRequest;
import dev.eduardojpanzo.user_management.dto.UserDTO.UserResponse;
import dev.eduardojpanzo.user_management.entity.User;
import dev.eduardojpanzo.user_management.exception.EmailAlreadyExistsException;
import dev.eduardojpanzo.user_management.exception.UserNotFoundException;
import dev.eduardojpanzo.user_management.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true) // leituras sem transacção de escrita por padrão
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional // override: esta operação escreve
    public UserResponse create(CreateUserRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        User user = new User(request.name(), request.email());
        User saved = repository.save(user);

        return UserResponse.from(saved);
    }

    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    public UserResponse findById(Long id) {
        return repository.findById(id)
                .map(UserResponse::from)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
