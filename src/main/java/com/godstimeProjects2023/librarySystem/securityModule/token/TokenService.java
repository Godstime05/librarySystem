package com.godstimeProjects2023.librarySystem.securityModule.token;


import com.godstimeProjects2023.librarySystem.repository.UserRepos;
import com.godstimeProjects2023.librarySystem.securityModule.auth.AuthResponse;
import com.godstimeProjects2023.librarySystem.securityModule.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

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

//    public TokenService(TokenRepo tokenRepo, UserRepos userRepo, JwtService jwtService) {
//        this.tokenRepo = tokenRepo;
//        this.userRepo = userRepo;
//        this.jwtService = jwtService;
//    }

    public Token createAuthToken(String username) {
        var user = userRepo.findByUsername(username).orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);

        return tokenRepo.save(Token.builder()
                .tokenType(TokenType.BEARER.name())
                .jwtToken(jwtToken)
                .refreshToken(UUID.randomUUID().toString())
                .user(userRepo.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("User not found")))
                .expirationDate(Instant.now().plusMillis(60000)) //10 minutes
                .build());
    }

    public Optional<Token> findByToken(String token) {
        return tokenRepo.findByRefreshToken(token);
    }

    public Token verifyExpirationDate(Token token) {
        if (token.getExpirationDate().compareTo(Instant.now()) < 0) {
            tokenRepo.delete(token);
            throw new RuntimeException("Refresh token expired. Sign in again");
        }
        return token;
    }

    public AuthResponse refreshToken(TokTokenRequest tokenRequest) {
        Token token = verifyExpirationDate(findByToken(tokenRequest.getTokenId()).orElseThrow(() -> new RuntimeException("Token not registered in Database")));
        String accessToken = createAuthToken(token.getUser().getUsername()).getJwtToken();
        return AuthResponse.builder().token(accessToken).refreshToken(token.getRefreshToken()).build();
    }
}
