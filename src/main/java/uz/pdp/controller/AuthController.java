package uz.pdp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.config.JwtTokenUtil;
import uz.pdp.dto.LoginDto;
import uz.pdp.dto.RegisterDto;
import uz.pdp.service.authService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final authService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(authService authService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterDto dto ){
        authService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public String login(@Valid @RequestBody LoginDto dto){

        authService.login(dto);

        return jwtTokenUtil.generateToken(dto.username());

    }

}
