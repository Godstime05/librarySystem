package com.godstimeProjects2023.librarySystem.securityModule.service;

import com.godstimeProjects2023.librarySystem.securityModule.token.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepo tokenRepo;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader= request.getHeader("Authorization");
        String jwt;

        if (authHeader==null || !authHeader.startsWith("Bearer ")){
            return;
        }
        jwt=authHeader.substring(7);
        var savedToken=tokenRepo.findByJwtToken(jwt).orElseThrow(null);

        if (savedToken != null){
            savedToken.setRevoked(true);
            savedToken.setExpired(true);
            tokenRepo.save(savedToken);
        }
    }
}
