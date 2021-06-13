package com.serratec.trabalhofinal.service;

import java.util.List;
import java.util.Optional;

import com.serratec.trabalhofinal.model.Cliente;
import com.serratec.trabalhofinal.model.Endereco;
import com.serratec.trabalhofinal.model.exception.ResourceBadRequestException;
import com.serratec.trabalhofinal.model.exception.ResourceNotFoundException;
import com.serratec.trabalhofinal.repository.ClienteRepository;
import com.serratec.trabalhofinal.repository.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ClienteService {
	
    @Autowired
    private ClienteRepository _repositorioCliente;
    
    @Autowired
    private EnderecoService servicoEndereco;
    
    @Autowired
    private EnderecoRepository _repositorioEndereco;

    public List<Cliente> obterTodos(){        
        return this._repositorioCliente.findAll();        
    }

    public ResponseEntity<Optional<Cliente>> obterPorId(@PathVariable(value = "id") Integer id){
        var cliente = _repositorioCliente.findById(id);    
        
        if(cliente.isEmpty()) {
        	throw new ResourceNotFoundException("Cliente não encontrado com o id " + id);
        } 
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente) {
    	if(cliente.getNome() == "" || cliente.getNome() == null ||
    			cliente.getCpf() == "" || cliente.getCpf() == null || cliente.getEmail() == "" ||
    			cliente.getEmail() == null || cliente.getSenha() == null || cliente.getSenha() == "" ||
    			cliente.getDataDeNascimento() == "" ||	cliente.getDataDeNascimento() == null) {
    		
    		throw new ResourceBadRequestException("Campos obrigatorios não informados ou vazios");
    	}
    	
    	cliente.setId(null);
     	Endereco endereco = servicoEndereco.ObterEnderecoPorCep(cliente.getEndereco().getCep());
     	cliente.setEndereco(endereco);
     	this._repositorioEndereco.save(endereco);
     	Cliente clienteNovo = this._repositorioCliente.save(cliente);
        return new ResponseEntity<>(clienteNovo, HttpStatus.CREATED);
    }

    public ResponseEntity<?> deletar(@PathVariable(value = "id") Integer id) {
    	var encontrado = _repositorioCliente.findById(id); 
    	
		if(encontrado.isEmpty()) {
			throw new ResourceNotFoundException("Cliente não encontrado com o id " + id);
	    }  
		this._repositorioCliente.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Cliente>> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Cliente cliente) {
		var clienteExiste = _repositorioCliente.findById(id);    
		
		if(clienteExiste.isEmpty()) {
			throw new ResourceNotFoundException("Cliente não encontrado com o id " + id);
	    }  
		cliente.setId(id);
     	Endereco endereco = servicoEndereco.ObterEnderecoPorCep(cliente.getEndereco().getCep());
     	cliente.setEndereco(endereco);
     	this._repositorioEndereco.save(endereco);
		this._repositorioCliente.save(cliente);
		return new ResponseEntity<>(clienteExiste, HttpStatus.OK);
    }
}
