package com.serratec.trabalhofinal.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.serratec.trabalhofinal.model.Categoria;
import com.serratec.trabalhofinal.model.exception.ResourceBadRequestException;
import com.serratec.trabalhofinal.model.exception.ResourceNotFoundException;
import com.serratec.trabalhofinal.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository _repositorioCategoria;
	
	public List<Categoria> obterTodos(){
		
		return this._repositorioCategoria.findAll();
	}
		
	public ResponseEntity<Optional<Categoria>> obterPorId(@PathVariable("id") Integer id) {
		Optional<Categoria> categoria = this._repositorioCategoria.findById(id);
		
		if(categoria.isEmpty()) {
			throw new ResourceNotFoundException("Não foi encontrada categoria com o id informado: " + id);
		}
		
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}
	
	public ResponseEntity<List<Categoria>> obterPorNome(String nome){
    	List<Categoria> categoria = _repositorioCategoria.findByNomeContainingIgnoreCase(nome);
		
    	if(categoria.isEmpty()) {
        	throw new ResourceNotFoundException("Não foi encontrado produto com o nome:" + nome);
       }
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}
	
	public ResponseEntity<Categoria> adicionar(@RequestBody Categoria categoria) {
		categoria.setId(null);
		
		if(!categoria.validoParaCadastro()) {
			throw new ResourceBadRequestException("Para cadastrar, informe o nome da categoria!");
		}
		
		var novaCategoria = _repositorioCategoria.save(categoria);
		return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
	}
	
	public ResponseEntity<Optional<Categoria>> atualizar(@PathVariable("id") Integer id, @RequestBody Categoria categoria){
		categoria.setId(id);
		
		var categoriaValida = _repositorioCategoria.findById(id);
		
		if(categoriaValida.isEmpty()) {
			throw new ResourceNotFoundException("Não existe categoria para o id informado: " + id);
		}
		
		this._repositorioCategoria.save(categoria);
		return new ResponseEntity<>(categoriaValida, HttpStatus.OK);	
	}
	
	public ResponseEntity<?> deletar(@PathVariable("id") Integer id){
		var existe = _repositorioCategoria.findById(id);
		
		if(existe.isEmpty()) {
			throw new ResourceNotFoundException("Não existe categoria para o id informado: " + id);	
		}
		
		this._repositorioCategoria.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
