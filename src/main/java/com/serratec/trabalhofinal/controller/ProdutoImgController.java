package com.serratec.trabalhofinal.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.serratec.trabalhofinal.service.ProdutoImgService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/img")
public class ProdutoImgController {
	
	@Autowired
	private ProdutoImgService servicoImgProduto;
	
	@ApiOperation(value = "Salva imagem para um produto")
	@PostMapping
	public void img(@RequestBody MultipartFile arquivo) throws IOException {
		
		servicoImgProduto.salvar("/serratec/img", arquivo);
				
	}
	
}
