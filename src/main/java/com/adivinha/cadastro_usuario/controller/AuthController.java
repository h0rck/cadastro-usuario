package com.adivinha.cadastro_usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adivinha.cadastro_usuario.domain.dto.AuthDTO;
import com.adivinha.cadastro_usuario.domain.dto.RegisterDTO;
import com.adivinha.cadastro_usuario.infra.entitys.User;
import com.adivinha.cadastro_usuario.infra.repository.UserRepository;
import com.adivinha.cadastro_usuario.infra.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated AuthDTO authDTO) {
        var userPassword = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password());
        var auth =  authenticationManager.authenticate(userPassword);

        var token  = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Validated RegisterDTO registerDTO) {
        if(this.userRepository.findByEmail(registerDTO.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        var user = new User(
            registerDTO.nome(),
            registerDTO.email(),
            encodedPassword,
            registerDTO.role()
        );
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

}