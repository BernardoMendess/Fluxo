package com.flx.fluxo.model.DAO;

import com.flx.fluxo.model.Transacao;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransacaoDAO extends CrudRepository<Transacao, Long> {

    @Query(
            "SELECT  t.* FROM transacao t " +
            "WHERE t.id_usuario = :idUsuario " +
            "ORDER BY t.id"
    )
    List<Transacao> findAllByidUsuario(long idUsuario);

    Transacao findObjById(long id);
}
