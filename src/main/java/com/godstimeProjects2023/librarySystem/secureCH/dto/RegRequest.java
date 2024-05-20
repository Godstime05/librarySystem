package com.godstimeProjects2023.librarySystem.secureCH.dto;

import com.godstimeProjects2023.librarySystem.secureCH.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Role role;
}
