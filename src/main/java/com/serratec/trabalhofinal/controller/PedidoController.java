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

import com.serratec.trabalhofinal.model.Pedido;
import com.serratec.trabalhofinal.service.PedidoService;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	@Autowired
    PedidoService _servicoPedido;
	

	@ApiOperation(value = "Retorna todos os pedidos")
    @GetMapping
    public List<Pedido> obterTodos(){
        return _servicoPedido.obterTodos();
    }
	
	@ApiOperation(value = "Retorna um pedido de acordo com o Id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> obterPorId(@PathVariable(value = "id") Integer id){
        return _servicoPedido.obterPorId(id);
    }

	@ApiOperation(value = "Adiciona um novo pedido")
    @PostMapping
    public ResponseEntity<Pedido> adicionar(@RequestBody Pedido pedido){
        return _servicoPedido.adicionar(pedido);
    }
    
	@ApiOperation(value = "Atualiza as informações de um pedido de acordo com o Id")
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Pedido pedido){
        return _servicoPedido.atualizar(id, pedido);
    }
    
    @PutMapping("/{pedido_id}/produto/{produto_id}")
    public Pedido relacionarPedidoComProduto(@PathVariable Integer pedido_id, @PathVariable Integer produto_id) {
    	return _servicoPedido.relacionarPedidoComProduto(pedido_id, produto_id);
    }
    
    @PutMapping("/{pedido_id}/cliente/{cliente_id}")
    public Pedido relacionarPedidoComCliente(@PathVariable Integer pedido_id, @PathVariable Integer cliente_id) {
    	return _servicoPedido.relacionarPedidoComCliente(pedido_id, cliente_id);
    }


	@ApiOperation(value = "Deleta um pedido de acordo com o Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Integer id){
        return _servicoPedido.deletar(id);
    }
    
}
