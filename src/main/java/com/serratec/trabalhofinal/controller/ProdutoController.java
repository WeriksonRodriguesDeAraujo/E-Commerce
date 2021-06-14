package com.serratec.trabalhofinal.controller;

import java.util.List;

import java.util.Optional;

import com.serratec.trabalhofinal.model.Pedido;
import com.serratec.trabalhofinal.model.Produto;
import com.serratec.trabalhofinal.service.ProdutoService;

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
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    ProdutoService _servicoProduto;

    @ApiOperation(value = "Retorna todos os produtos cadastrados")
    @GetMapping
    public List<Produto> obterTodos(){
        return _servicoProduto.obterTodos();
    }

    @ApiOperation(value = "Retorna os produtos cadastrados de acordo com o Id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> obterPorId(@PathVariable(value = "id") Integer id){
        return _servicoProduto.obterPorId(id);
    }
    

   @ApiOperation(value = "Retorna os produtos cadastrados de acordo com o nome ou parte do nome")
   @GetMapping("/nome/{nome}")
	 public ResponseEntity<List<Produto>> obterPorNome(@PathVariable ("nome") String nome) {
		  return _servicoProduto.obterPorNome(nome);
	 } 
    
    @ApiOperation(value = "Adiciona um novo produto")
    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody Produto Produto){
        return _servicoProduto.adicionar(Produto);
    }

    @ApiOperation(value = "Deleta um produto de acordo com o Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Integer id){
        return _servicoProduto.deletar(id);
    }

    @ApiOperation(value = "Atualiza as informações de um produto de acordo com o Id")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable(value = "id") Integer id, @RequestBody Produto Produto){
        return _servicoProduto.atualizar(id, Produto);
    }
    
    @PutMapping("/{produto_id}/categoria/{categoria_id}")
    public Produto relacionarProdutoComCategoria(@PathVariable Integer produto_id, @PathVariable Integer categoria_id) {
    	return _servicoProduto.relacionarProdutoComCategoria(produto_id, categoria_id);
    }
}
