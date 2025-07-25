package com.flx.fluxo.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;
}
