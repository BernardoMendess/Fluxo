package com.flx.fluxo.model.DAO;

import com.flx.fluxo.model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaDAO extends CrudRepository<Categoria, Long> {

    List<Categoria> findAll();
}
