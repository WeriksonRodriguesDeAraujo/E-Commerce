package com.serratec.trabalhofinal.controller;

import java.util.List;
import java.util.Optional;

import com.serratec.trabalhofinal.model.Cliente;
import com.serratec.trabalhofinal.service.ClienteService;

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

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    ClienteService _servicoCliente;


    @ApiOperation(value = "Retorna todos os clientes")
    @GetMapping
    public List<Cliente> obterTodos(){
        return _servicoCliente.obterTodos();
    }

    
    @ApiOperation(value = "Retor os clientes  de acordo com o Id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> obterPorId(@PathVariable(value = "id") Integer id){
        return _servicoCliente.obterPorId(id);
    }


    @ApiOperation(value = "Adiciona um novo cliente")
    @PostMapping
    public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente){
        return _servicoCliente.adicionar(cliente);
    }


    @ApiOperation(value = "Deleta um cliente de acordo com o Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Integer id){
        return _servicoCliente.deletar(id);
    }


    @ApiOperation(value = "Atualiza as informações de um cliente de acordo com o Id")
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Cliente cliente){
        return _servicoCliente.atualizar(id, cliente);
    }
}
