package com.flx.fluxo.model.login;

public record RegisterRequestDTO (
        String name,
        String email,
        String password
){}
