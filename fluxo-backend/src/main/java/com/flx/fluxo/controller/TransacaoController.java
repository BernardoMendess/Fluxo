package com.flx.fluxo.controller;

import com.flx.fluxo.model.Transacao;
import com.flx.fluxo.service.TransacaoService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Transacao>> list(){
        try{
            val transacoes = transacaoService.findAll();
            return ResponseEntity.ok(transacoes);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao recuperar transações: " + e.getMessage(), e);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<Transacao> get(@PathVariable long id) {
        try {
            val transacao = transacaoService.findById(id);
            return ResponseEntity.ok(transacao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter transação: " + e.getMessage(), e);
        }
    }

}
