package com.serratec.trabalhofinal.controller;

import com.serratec.trabalhofinal.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/endereco")
public class EndereoController {
    
    @Autowired
    private EnderecoService _servicoEndereco;


    @ApiOperation(value = "Obtem o endereço completo do cliente pelo CEP")
    @GetMapping("/{cep}")
    public ResponseEntity<?> obterEnderecoPorCep(@PathVariable("cep") String cep){

        var endereco = _servicoEndereco.ObterEnderecoPorCep(cep);

        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }
}
