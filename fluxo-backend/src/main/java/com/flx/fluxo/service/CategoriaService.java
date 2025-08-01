package com.flx.fluxo.service;

import com.flx.fluxo.model.Categoria;
import com.flx.fluxo.model.DAO.CategoriaDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaDAO categoriaDAO;

    public Categoria save(Categoria categoria) {
        return categoriaDAO.save(categoria);
    }

    public void deleteById(long id) {
        categoriaDAO.deleteById(id);
    }

    public List<Categoria> findAll(){
        return categoriaDAO.findAll();
    }

    public List<Categoria> findAllByTipoCategoria(String tipo){
        return categoriaDAO.findAllByTipoCategoria(tipo);
    }
}
