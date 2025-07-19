package com.flx.fluxo.controller;

import com.flx.fluxo.model.Categoria;
import com.flx.fluxo.service.CategoriaService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categoria")
@AllArgsConstructor
public class CategoriaController {

    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria){
        try {
            val categoriaSalva = categoriaService.save(categoria);
            return ResponseEntity.ok(categoriaSalva);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
