package com.flx.fluxo.controller;

import com.flx.fluxo.model.Transacao;
import com.flx.fluxo.service.TransacaoService;
import com.flx.fluxo.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transacao")
@AllArgsConstructor
public class TransacaoController {

    private TransacaoService transacaoService;

    private UserService userService;

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
            return ResponseEntity.ok(transacaoService.findAllByIdUsuarioAtual());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erro ao recuperar transações: " + e.getMessage(), e);
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
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
