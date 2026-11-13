package uz.pdp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.pdp.enums.Role;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "to_do")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "title is wrong")
    private String title;

    private boolean completed;

    private LocalDateTime createdAt;

    @Column(name = "user_id")
    private Long userid;

}
