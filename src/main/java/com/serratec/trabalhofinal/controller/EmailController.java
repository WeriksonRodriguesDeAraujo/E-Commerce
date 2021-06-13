package com.serratec.trabalhofinal.controller;

import com.serratec.trabalhofinal.model.email.Mailler;
import com.serratec.trabalhofinal.model.email.MensagemEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/email")
public class EmailController {

	@Autowired
	Mailler mailler;

  @ApiOperation(value = "Envia um email")
  @PostMapping
  public String enviarEmail(@RequestBody MensagemEmail email) {			
    try {
      mailler.enviar(email);
      return "Deu certo";

    } catch (Exception e) {
      e.printStackTrace();
      return "Deu ruim";
    }
  }
}
