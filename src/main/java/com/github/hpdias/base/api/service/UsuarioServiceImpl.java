package com.github.hpdias.base.api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.hpdias.base.api.dto.JwtResponseDto;
import com.github.hpdias.base.api.dto.LoginFormDto;
import com.github.hpdias.base.api.dto.SignUpFormDto;
import com.github.hpdias.base.api.entity.Role;
import com.github.hpdias.base.api.entity.Usuario;
import com.github.hpdias.base.api.enums.RoleNome;
import com.github.hpdias.base.api.repository.RoleRepository;
import com.github.hpdias.base.api.repository.UsuarioRepository;
import com.github.hpdias.base.api.security.jwt.JwtProvider;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
    AuthenticationManager authenticationManager;
	
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    UsuarioRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder encoder;

	@Override
	public ResponseEntity<?> login(LoginFormDto loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        
        return ResponseEntity.ok(new JwtResponseDto(jwt, jwtProvider.getUserNameFromJwtToken(jwt)));
	}

	@Override
	public ResponseEntity<?> cadastro(SignUpFormDto signUpRequest) {
		
        if(userRepository.existsByUsuario(signUpRequest.getUsuario())) {
            return new ResponseEntity<String>("Usuário já cadastrado!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("E-mail já cadastrado!",
                    HttpStatus.BAD_REQUEST);
        }
        
        
        Usuario user = new Usuario(signUpRequest.getNome(), signUpRequest.getUsuario(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getSenha()), signUpRequest.getCpf(),
        		signUpRequest.getCnpj(), signUpRequest.getEndBairro(), signUpRequest.getEndRua(), signUpRequest.getEndCep(), signUpRequest.getEndNumero());

        // Creating user's account

        Set<Role> roles = new HashSet<>();

        
    		Role userRole = roleRepository.findByNome(RoleNome.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Role não encontrado"));
    		roles.add(userRole);
        
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body("Usuário registrado com sucesso!");
		
	}
	
	

}
