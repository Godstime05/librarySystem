package com.godstimeProjects2023.librarySystem.securityModule.token;

import com.godstimeProjects2023.librarySystem.repository.UserRepos;
import com.godstimeProjects2023.librarySystem.securityModule.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepo tokenRepo;
    private final UserRepos userRepo;
    private final JwtService jwtService;

    public TokenService(TokenRepo tokenRepo, UserRepos userRepo, JwtService jwtService) {
        this.tokenRepo = tokenRepo;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    public Token createAuthToken (String username){
        var user = userRepo.findByUsername(username).orElseThrow();
        var jwtToken=jwtService.generateToken(user);

        return tokenRepo.save()
    }

}
