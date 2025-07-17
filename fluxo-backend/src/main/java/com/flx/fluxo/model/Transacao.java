package com.flx.fluxo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table(name = "transacao")
public class Transacao {

    @Id
    private long id;

    private long idUsuario;

    private int valor;

    private String descricao;

    private LocalDate dataTransacao;

    private LocalDate dataCriacao;

    private Tipo tipoTransacao;

    private int mesesRecorrencia;

    private long idCategoria;
}
