package com.example.controledeestoque.model.repository;

import com.example.controledeestoque.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}
