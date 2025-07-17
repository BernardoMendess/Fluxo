package com.flx.fluxo.controller;

import com.flx.fluxo.model.Transacao;
import com.flx.fluxo.service.TransacaoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transacao")
@AllArgsConstructor
public class TransacaoController {

    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Transacao> save(@RequestBody Transacao transacao) {
        try {
            val transacaoSalva = transacaoService.save(transacao);
            return ResponseEntity.ok(transacaoSalva);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar transação: " + e.getMessage(), e);
        }
    }

}
