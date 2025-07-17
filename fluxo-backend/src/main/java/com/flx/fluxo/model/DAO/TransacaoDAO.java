package com.flx.fluxo.model.DAO;

import com.flx.fluxo.model.Transacao;
import org.springframework.data.repository.CrudRepository;

public interface TransacaoDAO extends CrudRepository<Transacao, Long> {
}
