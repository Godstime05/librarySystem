package com.godstimeProjects2023.librarySystem.securityModule.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, String> {

    Optional<Token> findByJwtToken(String jwtToken);
    Optional<Token> findByRefreshToken(String token);
}
