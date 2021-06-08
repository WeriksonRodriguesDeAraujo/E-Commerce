package com.trabalhofinal.demo.controller;

import java.util.List;
import java.util.Optional;

import com.trabalhofinal.demo.model.Cliente;
import com.trabalhofinal.demo.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteService _servicoCliente;


    @GetMapping
    public List<Cliente> obterTodos(){
        return _servicoCliente.obterTodos();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> obterPorId(@PathVariable(value = "id") Integer id){
        return _servicoCliente.obterPorId(id);
    }


    @PostMapping
    public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente){
        return _servicoCliente.adicionar(cliente);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> deletar(@PathVariable(value = "id") Integer id){
        return _servicoCliente.deletar(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Cliente cliente){
        return _servicoCliente.atualizar(id, cliente);
    }
}
