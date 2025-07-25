package com.flx.fluxo.service;

import com.flx.fluxo.model.DAO.TransacaoDAO;
import com.flx.fluxo.model.Transacao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TransacaoService {

    private TransacaoDAO transacaoDAO;

    public Transacao save(Transacao transacao) {
        transacao.setDataCriacao(LocalDate.now());
        return transacaoDAO.save(transacao);
    }

    public Transacao findById(long id){
        return transacaoDAO.findObjById(id);
    }

    public List<Transacao> findAll(){
        return transacaoDAO.findAll();
    }
}
