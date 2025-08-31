package com.adivinha.cadastro_usuario.domain.dto;

public record RegisterDTO(
    String email,
    String password,
    String nome,
    String role

) {
}
