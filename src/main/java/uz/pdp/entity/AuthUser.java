package uz.pdp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.pdp.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "auth_user")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "username is wrong")
    @Column(unique = true,name = "user_name")
    private String username;

    @NotBlank(message = "password name is wrong")
    private String password;

    private Role role;


}
