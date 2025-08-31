package com.adivinha.cadastro_usuario.service;

import org.springframework.stereotype.Service;

import com.adivinha.cadastro_usuario.dto.UsuarioRequestDTO;
import com.adivinha.cadastro_usuario.dto.UsuarioResponseDTO;
import com.adivinha.cadastro_usuario.infrastructure.entitys.Usuario;
import com.adivinha.cadastro_usuario.infrastructure.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRequestDTO.toEntity();
        usuario = usuarioRepository.saveAndFlush(usuario);
        return UsuarioResponseDTO.fromEntity(usuario);
    }

    private Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado com o email: " + email)
        );
    }

    public UsuarioResponseDTO buscarDTOPorEmail(String email) {
        Usuario usuario = buscarPorEmail(email);
        return UsuarioResponseDTO.fromEntity(usuario);
    }

    public void deletarPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioResponseDTO atualizarPorEmail(String email, UsuarioRequestDTO usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorEmail(email);
        usuarioExistente.setNome(
            usuarioAtualizado.getNome() != null 
            ? usuarioAtualizado.getNome() 
            : usuarioExistente.getNome()
        );
        usuarioExistente = usuarioRepository.saveAndFlush(usuarioExistente);
        return UsuarioResponseDTO.fromEntity(usuarioExistente);
    }

}
