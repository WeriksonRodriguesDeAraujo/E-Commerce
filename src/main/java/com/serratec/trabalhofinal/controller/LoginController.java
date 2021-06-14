package com.serratec.trabalhofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.serratec.trabalhofinal.dto.LoginRequest;
import com.serratec.trabalhofinal.dto.LoginResponse;
import com.serratec.trabalhofinal.service.ClienteService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private ClienteService servicoCliente;
	
	@PostMapping
	public LoginResponse login (@RequestBody LoginRequest request) {
		  return servicoCliente.logar(request.getEmail(), request.getSenha());
	}
	
}
