package com.flx.fluxo.model.DAO;

import com.flx.fluxo.model.Transacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransacaoDAO extends CrudRepository<Transacao, Long> {

    List<Transacao> findAll();

    Transacao findObjById(long id);
}
