package com.serratec.trabalhofinal.service;

import java.util.List;

import java.util.Optional;

import com.serratec.trabalhofinal.model.Produto;
import com.serratec.trabalhofinal.model.exception.ResourceBadRequestException;
import com.serratec.trabalhofinal.model.exception.ResourceNotFoundException;
import com.serratec.trabalhofinal.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class ProdutoService {
	
	@Value("${caminho.raiz}")
	private String raiz;
	
    @Autowired
    private ProdutoRepository _repositorioProduto;

    public List<Produto> obterTodos(){     
    	
        return this._repositorioProduto.findAll();        
    }

    
    public ResponseEntity<Optional<Produto>> obterPorId(@PathVariable(value = "id") Integer id){
    	
        Optional<Produto> produto = _repositorioProduto.findById(id);
       
        if(produto.isEmpty()) {
        	throw new ResourceNotFoundException("Cliente não encontrado com o id " + id);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);  
    }
    
    
	 public ResponseEntity<List<Produto>> obterPorNome(String nome){
    	List<Produto> produto = _repositorioProduto.findByNomeContainingIgnoreCase(nome);
		
    	if(produto.isEmpty()) {
        	throw new ResourceNotFoundException("Não foi encontrado produto com o nome:" + nome);
       }
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	 
    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto) {
    	
    	if(produto.getNome() == "" || produto.getNome() == null || produto.getDescricao() == "" ||
    			produto.getDescricao() == null || produto.getPreco() == null ||
    			produto.getQuantidadeEmEstoque() == null || produto.getDataDeCadastro() == null) {
    		
    		throw new ResourceBadRequestException("Campos obrigatorios não informados ou vazios");
    	}
    	produto.setId(null);
        var produtoNovo = this._repositorioProduto.save(produto);
        return new ResponseEntity<>(produtoNovo, HttpStatus.CREATED);
    }

 
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Integer id) {
    	
    	Optional<Produto> produto = _repositorioProduto.findById(id);
        
    	if(produto.isEmpty()) {
    		throw new ResourceNotFoundException("Cliente não encontrado com o id " + id);
    	}
    	this._repositorioProduto.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
    }
    

    public ResponseEntity<Produto> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Produto produto) {
		
    		Optional<Produto> produtoExiste = _repositorioProduto.findById(id);
    		
    		if(produtoExiste.isEmpty()) {
    			throw new ResourceNotFoundException("Cliente não encontrado com o id " + id);
    		}
			produto.setId(id);
			Produto produtoAtualizado = this._repositorioProduto.save(produto);
			return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }
    
}
