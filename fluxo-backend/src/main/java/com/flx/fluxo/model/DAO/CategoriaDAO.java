package com.flx.fluxo.model.DAO;

import com.flx.fluxo.model.Categoria;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaDAO extends CrudRepository<Categoria, Long> {

    List<Categoria> findAll();

    @Query(
            "SELECT c.* FROM categoria c " +
            "WHERE c.tipo_categoria = :tipo " +
            "ORDER BY c.id"
    )
    List<Categoria> findAllByTipoCategoria(String tipo);
}
