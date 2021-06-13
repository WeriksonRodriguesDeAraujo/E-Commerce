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

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	@Autowired
    CategoriaService _servicoCategoria;
	
	@ApiOperation(value = "Retorna todas as categorias cadastradas")
    @GetMapping
    public List<Categoria> obterTodos(){
        return _servicoCategoria.obterTodos();
    }
	
	@ApiOperation(value = "Retorna as categorias de acordo com o Id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> obterPorId(@PathVariable(value = "id") Integer id){
        return _servicoCategoria.obterPorId(id);
    }
	
	@ApiOperation(value = "Retorna as categorias cadastradas de acordo com o nome ou parte do nome")
    @GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> obterPorNome(@PathVariable ("nome") String nome) {
		return _servicoCategoria.obterPorNome(nome);
	}
	
	@ApiOperation(value = "Adiciona uma nova categoria")
    @PostMapping
    public ResponseEntity<Categoria> adicionar(@RequestBody Categoria categoria){
        return _servicoCategoria.adicionar(categoria);
    }
    
	@ApiOperation(value = "Atualiza as informações de uma categoria de acordo com o Id")
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Categoria categoria){
        return _servicoCategoria.atualizar(id, categoria);
    }
	
	@ApiOperation(value = "Deleta uma categoria de acordo com o Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Integer id){
        return _servicoCategoria.deletar(id);
    }
    
}
