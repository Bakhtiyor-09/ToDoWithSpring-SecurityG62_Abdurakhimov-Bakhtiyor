package uz.pdp.service;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import uz.pdp.entity.AuthUser;
import uz.pdp.repository.AuthUserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AdminService {


    private final AuthUserRepository authUserRepository;

    public AdminService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    public Optional<List<AuthUser>> getAllUsers() {

        List<AuthUser> all = authUserRepository.findAll();

        all.forEach(u->u.setPassword(null));

        return Optional.of(all);
    }
}
