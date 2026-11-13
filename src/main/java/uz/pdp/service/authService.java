package uz.pdp.service;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.dto.LoginDto;
import uz.pdp.dto.RegisterDto;
import uz.pdp.entity.AuthUser;
import uz.pdp.enums.Role;
import uz.pdp.exceptions.UserAlreadyExistsException;
import uz.pdp.repository.AuthUserRepository;

import java.util.Optional;

@Component
public class authService {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public authService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void save(@Valid RegisterDto dto) {

        Optional<AuthUser> optional = authUserRepository.findAuthUserByUsername(dto.username());

        if ( optional.isPresent() ) throw new UserAlreadyExistsException( "Username: " + dto.username());

        AuthUser authUser = new AuthUser();
        authUser.setUsername(dto.username());
        authUser.setPassword( passwordEncoder.encode(dto.password()) );
        authUser.setRole( Role.ROLE_USER );

        authUserRepository.save(authUser);
    }

    public void login(@Valid LoginDto dto) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());

        authenticationManager.authenticate(authentication);


    }

    public Optional<AuthUser> getIdByUsername(String name) {

        Optional<AuthUser> authUsersByUsername = authUserRepository.getAuthUsersByUsername(name);

        return authUsersByUsername;
    }
}
