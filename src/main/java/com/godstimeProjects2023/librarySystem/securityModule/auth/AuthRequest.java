package com.godstimeProjects2023.librarySystem.securityModule.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
