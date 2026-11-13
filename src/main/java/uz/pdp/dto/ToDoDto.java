package uz.pdp.dto;

import jakarta.validation.constraints.NotBlank;
import uz.pdp.entity.ToDo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ToDo}
 */
public record ToDoDto(@NotBlank(message = "title is wrong") String title, LocalDateTime createdAt,Long userId)  {
}