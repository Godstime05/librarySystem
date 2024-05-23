package com.godstimeProjects.librarySystem.secureCH.auth;

import com.godstimeProjects.librarySystem.secureCH.dto.AuthRequest;
import com.godstimeProjects.librarySystem.secureCH.dto.AuthResponse;
import com.godstimeProjects.librarySystem.secureCH.dto.RegRequest;
import com.godstimeProjects.librarySystem.secureCH.entity.SecuredUser;
import com.godstimeProjects.librarySystem.secureCH.jwt.JwtService;
import com.godstimeProjects.librarySystem.secureCH.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authMgr;

    public AuthResponse register(RegRequest regRequest){
        var user = SecuredUser.builder()
                .firstName(regRequest.getFirstname())
                .lastName(regRequest.getLastname())
                .email(regRequest.getEmail())
                .username(regRequest.getUsername())
                .password(passwordEncoder.encode(regRequest.getPassword()))
                .role(regRequest.getRole())
                .build();
        var saveUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse authenticate(AuthRequest authRequest){

        authMgr.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword()
        ));

        var user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

}
