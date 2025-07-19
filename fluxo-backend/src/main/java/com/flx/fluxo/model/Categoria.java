package com.flx.fluxo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "categoria")
@Data
public class Categoria {

    @Id
    private long id;

    private String nome;

    private Tipo tipoCategoria;
}
