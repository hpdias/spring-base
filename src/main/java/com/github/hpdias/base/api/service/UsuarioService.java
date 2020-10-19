package com.github.hpdias.base.api.service;

import javax.persistence.EntityManager;

import org.springframework.http.ResponseEntity;

import com.github.hpdias.base.api.dto.JwtResponseDto;
import com.github.hpdias.base.api.dto.LoginFormDto;
import com.github.hpdias.base.api.dto.SignUpFormDto;

public interface UsuarioService {
	
	ResponseEntity<?> login(LoginFormDto loginRequest);
	ResponseEntity<?> cadastro(SignUpFormDto signUpRequest);

}
