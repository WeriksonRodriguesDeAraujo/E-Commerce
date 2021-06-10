package com.serratec.trabalhofinal.controller;

import java.util.List;

import java.util.Optional;

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

import com.serratec.trabalhofinal.model.Categoria;
import com.serratec.trabalhofinal.service.CategoriaService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/categorias")
public class CategoriaController {
	@Autowired
    CategoriaService _servicoCategoria;

    @GetMapping
    public List<Categoria> obterTodos(){
        return _servicoCategoria.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> obterPorId(@PathVariable(value = "id") Integer id){
        return _servicoCategoria.obterPorId(id);
    }

    @PostMapping
    public ResponseEntity<Categoria> adicionar(@RequestBody Categoria categoria){
        return _servicoCategoria.adicionar(categoria);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Categoria categoria){
        return _servicoCategoria.atualizar(id, categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Integer id){
        return _servicoCategoria.deletar(id);
    }
    
}
