package uz.pdp.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> getAuthUsersByUsername(String username);

    Optional<AuthUser> findAuthUserByUsername(@NotBlank(message = "username is wrong") String username);
}