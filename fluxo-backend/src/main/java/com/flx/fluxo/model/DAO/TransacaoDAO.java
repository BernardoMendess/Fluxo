package com.flx.fluxo.model.DAO;

import com.flx.fluxo.model.Transacao;
import lombok.val;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoDAO extends CrudRepository<Transacao, Long> {

    @Query("SELECT t.* FROM transacao t " +
            "WHERE t.id_usuario = :idUsuario " +
            "AND (:categoria IS NULL OR t.tipo_transacao LIKE LOWER(CONCAT('%', :categoria, '%'))) " +
            "AND (:descricao IS NULL OR LOWER(t.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))) " +
            "ORDER BY t.data_transacao DESC")
    List<Transacao> findAllByidUsuarioSemData(long idUsuario, String categoria, String descricao);

    @Query("SELECT t.* FROM transacao t " +
            "WHERE t.id_usuario = :idUsuario " +
            "AND (:categoria IS NULL OR t.tipo_transacao LIKE LOWER(CONCAT('%', :categoria, '%'))) " +
            "AND (:descricao IS NULL OR LOWER(t.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))) " +
            "AND t.data_transacao BETWEEN :dataInicial AND :dataFinal " +
            "ORDER BY t.data_transacao DESC")
    List<Transacao> findAllByidUsuarioAndFiltros(long idUsuario, String categoria, String descricao, LocalDate dataInicial, LocalDate dataFinal);

    Transacao findObjById(long id);
}
