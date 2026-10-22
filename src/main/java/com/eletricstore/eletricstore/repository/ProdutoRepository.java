package com.eletricstore.eletricstore.repository;

import com.eletricstore.eletricstore.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaId(Long categoriaId);

    boolean existsByCategoriaId(Long categoriaId);
}