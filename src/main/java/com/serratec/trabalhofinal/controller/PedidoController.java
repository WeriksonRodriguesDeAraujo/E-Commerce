package com.trabalhofinal.demo.controller;

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

import com.trabalhofinal.demo.model.Pedido;
import com.trabalhofinal.demo.service.PedidoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	@Autowired
    PedidoService _servicoPedido;

    @GetMapping
    public List<Pedido> obterTodos(){
        return _servicoPedido.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> obterPorId(@PathVariable(value = "id") Integer id){
        return _servicoPedido.obterPorId(id);
    }

    @PostMapping
    public ResponseEntity<Pedido> adicionar(@RequestBody Pedido pedido){
        return _servicoPedido.adicionar(pedido);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Pedido pedido){
        return _servicoPedido.atualizar(id, pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Integer id){
        return _servicoPedido.deletar(id);
    }
    
}
