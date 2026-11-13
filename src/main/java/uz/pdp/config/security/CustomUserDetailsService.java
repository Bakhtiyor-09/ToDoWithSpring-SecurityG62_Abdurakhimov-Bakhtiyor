package uz.pdp.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.pdp.entity.AuthUser;
import uz.pdp.repository.AuthUserRepository;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthUserRepository authUserRepository;

    public CustomUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> optional = authUserRepository.getAuthUsersByUsername(username);

        AuthUser authUser = optional.orElseThrow(() -> new UsernameNotFoundException("user is not found with username : " + username));

        return new CustomUserDetails(authUser);
    }
}
