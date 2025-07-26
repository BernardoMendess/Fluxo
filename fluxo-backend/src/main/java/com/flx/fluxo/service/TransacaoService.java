package com.flx.fluxo.service;

import com.flx.fluxo.model.DAO.TransacaoDAO;
import com.flx.fluxo.model.Transacao;
import com.flx.fluxo.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TransacaoService {

    private TransacaoDAO transacaoDAO;

    private UserService userService;

    public Transacao save(Transacao transacao) {
        transacao.setDataCriacao(LocalDate.now());
        return transacaoDAO.save(transacao);
    }

    public Transacao findById(long id){
        return transacaoDAO.findObjById(id);
    }

    public List<Transacao> findAllByIdUsuarioAtual(){
        return transacaoDAO.findAllByidUsuario(userService.findIdUsuarioAtual());
    }
}
