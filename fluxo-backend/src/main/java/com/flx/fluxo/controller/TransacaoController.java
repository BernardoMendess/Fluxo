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
            return ResponseEntity.ok(transacaoService.save(transacao));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar transação: " + e.getMessage(), e);
        }
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> list(){
        try{
            return ResponseEntity.ok(transacaoService.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao recuperar transações: " + e.getMessage(), e);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<Transacao> get(@PathVariable long id) {
        try {
            return ResponseEntity.ok(transacaoService.findById(id));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter transação: " + e.getMessage(), e);
        }
    }

}
