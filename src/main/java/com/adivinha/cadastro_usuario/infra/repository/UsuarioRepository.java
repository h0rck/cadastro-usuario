package com.adivinha.cadastro_usuario.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.adivinha.cadastro_usuario.infra.entitys.Usuario;

import jakarta.transaction.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
