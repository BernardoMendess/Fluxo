package com.flx.fluxo.service;

import com.flx.fluxo.model.Categoria;
import com.flx.fluxo.model.DAO.CategoriaDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaDAO categoriaDAO;

    public Categoria save(Categoria categoria) {
        return categoriaDAO.save(categoria);
    }
}
