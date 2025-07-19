package com.flx.fluxo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tipo {
    ENTRADA("Entrada"),
    SAIDA("Saída");

    private final String nome;
}
