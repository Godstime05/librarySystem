package com.godstimeProjects2023.librarySystem.secureCH.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    MEMBER_READ("mgt:read"),
    MEMBER_CREATE("mgt:create"),
    ;
    @Getter
    private final String permission;
}
