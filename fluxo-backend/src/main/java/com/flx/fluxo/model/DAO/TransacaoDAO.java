package com.flx.fluxo.model.DAO;

import com.flx.fluxo.model.Transacao;
import lombok.val;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoDAO extends CrudRepository<Transacao, Long> {

    @Query("SELECT  t.* FROM transacao t " +
            "WHERE t.id_usuario = :idUsuario " +
            "ORDER BY t.data_transacao")
    List<Transacao> findAllByidUsuarioSemData(long idUsuario);

    @Query("SELECT  t.* FROM transacao t " +
            "INNER JOIN categoria c ON c.id = t.id_categoria " +
            "WHERE t.id_usuario = :idUsuario " +
            "AND c.nome = :categoria " +
            "AND t.descricao = :descricao " +
            "AND t.data_transacao BETWEEN :dataInicial AND :dataFinal " +
            "ORDER BY t.data_transacao")
    List<Transacao> findAllByidUsuarioAndFiltros(long idUsuario, String categoria, String descricao, LocalDate dataInicial, LocalDate dataFinal);

    Transacao findObjById(long id);
}
