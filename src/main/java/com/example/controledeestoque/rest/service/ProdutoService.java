package com.example.controledeestoque.rest.service;

import com.example.controledeestoque.model.entity.Produto;
import com.example.controledeestoque.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> buscarProdutos(){
        return repository.findAll();
    }

    public Produto salvar(@RequestBody Produto produto){
        return repository.save(produto);
    }
}
