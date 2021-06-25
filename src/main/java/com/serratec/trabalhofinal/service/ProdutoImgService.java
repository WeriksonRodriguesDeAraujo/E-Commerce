package com.serratec.trabalhofinal.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.serratec.trabalhofinal.model.exception.ResourceBadRequestException;


@Service
public class ProdutoImgService {
	
	@Value("${caminho.raiz}")
	private String raiz;
	 
	public void salvar(String caminho, MultipartFile arquivo) {
		
		Path diretorio = Paths.get(this.raiz, caminho);
		Path arquivoPath = diretorio.resolve(arquivo.getOriginalFilename());
			
		try {
			
			Files.createDirectories(diretorio);
			arquivo.transferTo(arquivoPath.toFile());
			
		} catch (Exception e) {
			throw new ResourceBadRequestException("Falha ao tentar salvar o arquivo!");
		}
		
	}
	
}
