package uz.pdp.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.entity.AuthUser}
 */
public record RegisterDto(@NotBlank(message = "username is wrong") String username,
                          @NotBlank(message = "password name is wrong") String password) implements Serializable {
}