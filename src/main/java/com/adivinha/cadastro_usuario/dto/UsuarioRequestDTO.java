package com.adivinha.cadastro_usuario.dto;

import com.adivinha.cadastro_usuario.infrastructure.entitys.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDTO {
    private String nome;
    private String email;
    private String senha;

    // Método para mapear DTO para a entidade
    public Usuario toEntity() {
        return Usuario.builder()
                .nome(this.nome)
                .email(this.email)
                .senha(this.senha)
                .build();
    }
}
