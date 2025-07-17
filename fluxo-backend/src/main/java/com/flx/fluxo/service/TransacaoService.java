package com.flx.fluxo.service;

import com.flx.fluxo.model.DAO.TransacaoDAO;
import com.flx.fluxo.model.Transacao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransacaoService {

    private TransacaoDAO transacaoDAO;

    public Transacao save(Transacao transacao) {
        return transacaoDAO.save(transacao);
    }

    public Transacao findById(long id){
        return transacaoDAO.findById(id).get();
    }
}
