package com.adivinha.cadastro_usuario.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.adivinha.cadastro_usuario.infra.entitys.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
