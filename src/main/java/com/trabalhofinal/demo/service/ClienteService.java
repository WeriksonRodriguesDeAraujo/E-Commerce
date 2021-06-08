package com.trabalhofinal.demo.service;

import java.util.List;
import java.util.Optional;

import com.trabalhofinal.demo.model.Cliente;
import com.trabalhofinal.demo.repository.ClienteRepository;

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

    
    public List<Cliente> obterTodos(){        
        return this._repositorioCliente.findAll();        
    }


    public ResponseEntity<Optional<Cliente>> obterPorId(@PathVariable(value = "id") Integer id){
		
    try { 
        var encontrado = _repositorioCliente.findById(id);
                    
        return new ResponseEntity<Optional<Cliente>>(encontrado, HttpStatus.OK);
                    
        } catch (Exception e) {
              
            System.out.println(e.getMessage());
                        
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente) {

        var adicionado = this._repositorioCliente.save(cliente);

        return new ResponseEntity<Cliente>(adicionado, HttpStatus.CREATED);
    }


    public ResponseEntity<Optional<Cliente>> deletar(@PathVariable(value = "id") Integer id) {

		try {
			
        this._repositorioCliente.deleteById(id);;
		
		return new ResponseEntity<Optional<Cliente>>(HttpStatus.OK);
		
		} catch (Exception e) {
			
			System.out.println(e.getMessage()); 
			
			return new ResponseEntity<Optional<Cliente>>(HttpStatus.NOT_FOUND);
		}
    }


    public ResponseEntity<Cliente> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Cliente cliente) {
		
		try {

		cliente.setId(id);
			
		var clienteAtualizado = this._repositorioCliente.save(cliente);
			
		return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
    }
}
