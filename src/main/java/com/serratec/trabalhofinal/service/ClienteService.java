package com.serratec.trabalhofinal.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.serratec.trabalhofinal.dto.LoginResponse;
import com.serratec.trabalhofinal.model.Cliente;
import com.serratec.trabalhofinal.model.Endereco;
import com.serratec.trabalhofinal.model.exception.ResourceBadRequestException;
import com.serratec.trabalhofinal.model.exception.ResourceNotFoundException;
import com.serratec.trabalhofinal.repository.ClienteRepository;
import com.serratec.trabalhofinal.repository.EnderecoRepository;
import com.serratec.trabalhofinal.security.JWTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private PasswordEncoder passwordEncoder;
    
//08048203f5fac8ea1795bb623ebc23b4ca3352b1

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
     	
    	if(_repositorioCliente.findByEmail(cliente.getEmail()).isPresent()) {
    		
    	}
    	
    	String senha = passwordEncoder.encode(cliente.getSenha());
    	cliente.setSenha(senha);
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
    
    private static final String headerPrefix = "Bearer ";
    
    @Autowired
    private JWTService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    public LoginResponse logar(String email, String senha) {
    
    	Authentication autenticacao = authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));
    	
    	SecurityContextHolder.getContext().setAuthentication(autenticacao);
    	
    	String token = headerPrefix + jwtService.gerarToken(autenticacao);
    	
    	var usuario = _repositorioCliente.findByEmail(email);
    	return new LoginResponse(token, usuario.get());
    }
    
    
}
