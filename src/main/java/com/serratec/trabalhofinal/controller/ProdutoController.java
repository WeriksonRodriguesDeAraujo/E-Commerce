package com.serratec.trabalhofinal.controller;

import java.util.List;
import java.util.Optional;

import com.serratec.trabalhofinal.model.Produto;
import com.serratec.trabalhofinal.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    ProdutoService _servicoProduto;

    @GetMapping
    public List<Produto> obterTodos(){
        return _servicoProduto.obterTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> obterPorId(@PathVariable(value = "id") Integer id){
        return _servicoProduto.obterPorId(id);
    }

    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody Produto Produto){
        return _servicoProduto.adicionar(Produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Integer id){
        return _servicoProduto.deletar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Produto Produto){
        return _servicoProduto.atualizar(id, Produto);
    }
}
