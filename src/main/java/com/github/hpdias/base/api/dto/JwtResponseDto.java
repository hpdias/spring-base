package com.github.hpdias.base.api.dto;

import lombok.Data;

@Data
public class JwtResponseDto {
    private String token;
    private String usuario;
    private String type = "Bearer";

    public JwtResponseDto(String accessToken, String usuario) {
        this.token = accessToken;
        this.usuario = usuario;
    }
}