package com.trabalhofinal.demo.service;

import java.util.List;
import java.util.Optional;

import com.trabalhofinal.demo.model.Produto;
import com.trabalhofinal.demo.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class ProdutoService {
    @Autowired
    private ProdutoRepository _repositorioProduto;

    
    public List<Produto> obterTodos(){        
        return this._repositorioProduto.findAll();        
    }


    public ResponseEntity<Optional<Produto>> obterPorId(@PathVariable(value = "id") Integer id){
		
    try { 
        var encontrado = _repositorioProduto.findById(id);
                    
        return new ResponseEntity<Optional<Produto>>(encontrado, HttpStatus.OK);
                    
        } catch (Exception e) {
              
            System.out.println(e.getMessage());
                        
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto) {

        var adicionado = this._repositorioProduto.save(produto);

        return new ResponseEntity<Produto>(adicionado, HttpStatus.CREATED);
    }


    public ResponseEntity<Optional<Produto>> deletar(@PathVariable(value = "id") Integer id) {

		try {
			
        this._repositorioProduto.deleteById(id);;
		
		return new ResponseEntity<Optional<Produto>>(HttpStatus.OK);
		
		} catch (Exception e) {
			
			System.out.println(e.getMessage()); 
			
			return new ResponseEntity<Optional<Produto>>(HttpStatus.NOT_FOUND);
		}
    }


    public ResponseEntity<Produto> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Produto produto) {
		
		try {

		produto.setId(id);
			
		var ProdutoAtualizado = this._repositorioProduto.save(produto);
			
		return new ResponseEntity<>(ProdutoAtualizado, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
    }
}
