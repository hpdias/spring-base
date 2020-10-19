package com.github.hpdias.base.api.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hpdias.base.api.dto.JwtResponseDto;
import com.github.hpdias.base.api.dto.LoginFormDto;
import com.github.hpdias.base.api.dto.SignUpFormDto;
import com.github.hpdias.base.api.entity.Role;
import com.github.hpdias.base.api.entity.Usuario;
import com.github.hpdias.base.api.enums.RoleNome;
import com.github.hpdias.base.api.repository.RoleRepository;
import com.github.hpdias.base.api.repository.UsuarioRepository;
import com.github.hpdias.base.api.security.jwt.JwtProvider;
import com.github.hpdias.base.api.service.UsuarioService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UsuarioNoAuthController {
    
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginFormDto loginRequest) {

        return ResponseEntity.ok(usuarioService.login(loginRequest));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpFormDto signUpRequest) {

        return usuarioService.cadastro(signUpRequest);
        
    }
}