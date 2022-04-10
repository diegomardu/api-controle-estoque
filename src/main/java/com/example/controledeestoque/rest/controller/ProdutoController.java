package com.example.controledeestoque.rest.controller;

import com.example.controledeestoque.model.entity.Produto;
import com.example.controledeestoque.model.repository.ProdutoRepository;
import com.example.controledeestoque.rest.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    private List<Produto> listarProdutos(){
        return service.buscarProdutos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Produto salvar(@RequestBody Produto produto){
        return service.salvar(produto);
    }

    @GetMapping("{id}")
    private Produto buscarProduto(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encotrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map( produto -> {
                    repository.delete(produto);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id,@RequestBody Produto produtoAtualizado){
        repository
                .findById(id)
                .map(produto -> {
                    produtoAtualizado.setId(produto.getId());
                    produtoAtualizado.setQuantidade(produto.getQuantidade() + produtoAtualizado.getQuantidade());
                    return repository.save(produtoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado"));
    }
}
