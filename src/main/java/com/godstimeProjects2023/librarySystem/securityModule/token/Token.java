package com.godstimeProjects2023.librarySystem.securityModule.token;

import com.godstimeProjects2023.librarySystem.entity.User;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    @Id
    private String id;
    private String refreshToken;
    private String jwtToken;
    private String tokenType;
    private boolean revoked;
    private boolean expired;
    private Instant expirationDate;

    private User user;
}
