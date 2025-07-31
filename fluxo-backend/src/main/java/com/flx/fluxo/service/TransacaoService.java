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
        transacao.setIdUsuario(userService.findIdUsuarioAtual());
        transacao.setDataCriacao(LocalDate.now());
        return transacaoDAO.save(transacao);
    }

    public Transacao findById(long id){
        return transacaoDAO.findObjById(id);
    }

    public List<Transacao> findAllByIdUsuarioAtual(LocalDate data, String categoria, String descricao){
        return findAllByidUsuario(userService.findIdUsuarioAtual(), data, categoria, descricao);
    }

    private List<Transacao> findAllByidUsuario(long idUsuario, LocalDate data, String categoria, String descricao){
        if (categoria != null && categoria.trim().isEmpty()) categoria = null;
        if (descricao != null && descricao.trim().isEmpty()) descricao = null;

        if(data == null){
            return transacaoDAO.findAllByidUsuarioSemData(idUsuario, categoria, descricao);
        }

        return transacaoDAO.findAllByidUsuarioAndFiltros(idUsuario, categoria, descricao, data, LocalDate.now());
    }

    public void deleteById(long id) {
        transacaoDAO.deleteById(id);
    }
}
