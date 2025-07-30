package com.flx.fluxo.model.DAO;

import com.flx.fluxo.model.Transacao;
import lombok.val;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoDAO extends CrudRepository<Transacao, Long> {

    String QUERY_SELECT_ALL_TRANSACOES_USUARIO =
            "SELECT  t.* FROM transacao t " +
            "WHERE t.id_usuario = :idUsuario ";

    @Query(QUERY_SELECT_ALL_TRANSACOES_USUARIO +
            "ORDER BY t.data_transacao")
    List<Transacao> findAllByidUsuarioSemData(long idUsuario);

    @Query(QUERY_SELECT_ALL_TRANSACOES_USUARIO +
            "AND t.data_transacao BETWEEN :dataInicial AND :dataFinal " +
            "ORDER BY t.data_transacao")
    List<Transacao> findAllByidUsuarioComData(long idUsuario, LocalDate dataInicial, LocalDate dataFinal);

    Transacao findObjById(long id);
}
