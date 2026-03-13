package dev.eduardojpanzo.user_management.service;

import dev.eduardojpanzo.user_management.dto.UserDTO.CreateUserRequest;
import dev.eduardojpanzo.user_management.dto.UserDTO.UserResponse;
import dev.eduardojpanzo.user_management.exception.EmailAlreadyExistsException;
import dev.eduardojpanzo.user_management.exception.UserNotFoundException;
import dev.eduardojpanzo.user_management.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional // rollback após cada teste
class UserServiceTest {

    @Autowired UserService service;
    @Autowired UserRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar utilizador com sucesso")
    void shouldCreateUser() {
        CreateUserRequest request = new CreateUserRequest("Maria Silva", "maria@exemplo.ao");

        UserResponse response = service.create(request);

        assertThat(response.id()).isNotNull();
        assertThat(response.name()).isEqualTo("Maria Silva");
        assertThat(response.email()).isEqualTo("maria@exemplo.ao");
        assertThat(response.createdAt()).isNotNull();
    }

    @Test
    @DisplayName("Deve lançar excepção quando email já existe")
    void shouldThrowWhenEmailAlreadyExists() {
        CreateUserRequest request = new CreateUserRequest("João", "joao@exemplo.ao");
        service.create(request);

        assertThatThrownBy(() -> service.create(request))
                .isInstanceOf(EmailAlreadyExistsException.class)
                .hasMessageContaining("joao@exemplo.ao");
    }

    @Test
    @DisplayName("Deve listar todos os utilizadores")
    void shouldListAllUsers() {
        service.create(new CreateUserRequest("Ana", "ana@exemplo.ao"));
        service.create(new CreateUserRequest("Bruno", "bruno@exemplo.ao"));

        List<UserResponse> users = service.findAll();

        assertThat(users).hasSize(2);
    }

    @Test
    @DisplayName("Deve encontrar utilizador por id")
    void shouldFindById() {
        UserResponse created = service.create(new CreateUserRequest("Carlos", "carlos@exemplo.ao"));

        UserResponse found = service.findById(created.id());

        assertThat(found.email()).isEqualTo("carlos@exemplo.ao");
    }

    @Test
    @DisplayName("Deve lançar excepção quando utilizador não existe")
    void shouldThrowWhenUserNotFound() {
        assertThatThrownBy(() -> service.findById(999L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("999");
    }

    @Test
    @DisplayName("Deve eliminar utilizador com sucesso")
    void shouldDeleteUser() {
        UserResponse created = service.create(new CreateUserRequest("Dora", "dora@exemplo.ao"));

        service.delete(created.id());

        assertThat(repository.existsById(created.id())).isFalse();
    }

    @Test
    @DisplayName("Deve lançar excepção ao eliminar id inexistente")
    void shouldThrowWhenDeletingNonExistentUser() {
        assertThatThrownBy(() -> service.delete(999L))
                .isInstanceOf(UserNotFoundException.class);
    }
}
