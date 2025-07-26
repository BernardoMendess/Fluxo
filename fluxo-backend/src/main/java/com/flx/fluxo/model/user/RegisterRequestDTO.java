package com.flx.fluxo.model.user;

public record RegisterRequestDTO (
        String name,
        String email,
        String password
){}
