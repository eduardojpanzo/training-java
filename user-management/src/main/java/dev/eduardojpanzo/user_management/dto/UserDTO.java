package dev.eduardojpanzo.user_management.dto;

import dev.eduardojpanzo.user_management.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;

public class UserDTO {

    /**
     * Payload de entrada para criar um utilizador.
     * Validado automaticamente pelo Bean Validation via @Valid no Controller.
     */
    public record CreateUserRequest(
            @NotBlank(message = "Nome é obrigatório")
            @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
            String name,

            @NotBlank(message = "Email é obrigatório")
            @Email(message = "Email inválido")
            String email
    ) {}

    /**
     * Payload de saída — nunca expõe a Entity directamente.
     * Isola o contrato da API do modelo interno de dados.
     */
    public record UserResponse(
            Long id,
            String name,
            String email,
            OffsetDateTime createdAt
    ) {
        public static UserResponse from(User user) {
            return new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt()
            );
        }
    }
}
