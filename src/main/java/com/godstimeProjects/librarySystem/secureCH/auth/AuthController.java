package com.godstimeProjects.librarySystem.secureCH.auth;

import com.godstimeProjects.librarySystem.secureCH.dto.AuthRequest;
import com.godstimeProjects.librarySystem.secureCH.dto.AuthResponse;
import com.godstimeProjects.librarySystem.secureCH.dto.RegRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegRequest request){
        AuthResponse authResponse = authenticationService.register(request);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    }
