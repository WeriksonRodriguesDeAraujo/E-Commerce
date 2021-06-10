package com.trabalhofinal.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.trabalhofinal.demo.model.Categoria;
import com.trabalhofinal.demo.model.Pedido;
import com.trabalhofinal.demo.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository _repositorioPedido;
	
	public List<Pedido> obterTodos(){
		
		return this._repositorioPedido.findAll();	
	}
	
	
	public ResponseEntity<Optional<Pedido>> obterPorId(@PathVariable("id") Integer id) {
		Optional<Pedido> pedido = this._repositorioPedido.findById(id);
		
		if(pedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi encontrado pedido com o id informado: " + id);
		}
		
		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}
	
	
	public ResponseEntity<Pedido> adicionar(@RequestBody Pedido pedido) {
		pedido.setId(null);
		
		if(!pedido.validoParaCadastro()) {
			throw new ResourceBadRequestException("Para cadastrar, informe o(s) produto(s) do pedido!");
		}
		
		pedido = _repositorioPedido.save(pedido);
		return new ResponseEntity<>(pedido, HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<Optional<Pedido>> atualizar(@PathVariable("id") Integer id, @RequestBody Pedido pedido){
		pedido.setId(id);
		
		var pedidoValido = _repositorioPedido.findById(id);
		
		if(pedidoValido.isEmpty()) {
			throw ResourceNotFoundException("Não existe pedido com o id informado: " + id);
		}
		
		this._repositorioPedido.save(pedido);
		return new ResponseEntity<>(pedidoValido, HttpStatus.OK);	
	}
	
	
	public ResponseEntity<?> deletar(@PathVariable("id") Integer id){
		var existe = _repositorioPedido.findById(id);
		
		if(existe.isEmpty()) {
			throw new ResourceNotFoundException("Não existe pedido com o id informado: " + id);	
		}
		
		this._repositorioPedido.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
}
