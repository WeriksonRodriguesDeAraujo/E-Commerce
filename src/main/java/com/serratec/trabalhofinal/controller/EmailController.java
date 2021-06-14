package com.serratec.trabalhofinal.controller;

import java.util.Arrays;

import com.serratec.trabalhofinal.model.email.Mailler;
import com.serratec.trabalhofinal.model.email.MensagemEmail;
import com.serratec.trabalhofinal.service.PedidoService;

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
	
	@Autowired
	PedidoService _servicoPedido;

  @ApiOperation(value = "Envia um email")
  @PostMapping
  public String enviarEmail(@RequestBody MensagemEmail email) {			
    
    var email = new MensagemEmail(
			  "Informações do Pedido",
			  "Data de Entrega: 17/06/2021 " + _servicoPedido.obterTodos().size(),
        "E-Commerce <serratecdev@gmail.com>",
        Arrays.asList("Dudu <luizeduardo15012@gmail.com>"));

    try {
      mailler.enviar(email);
      return "Email enviado";

    } catch (Exception e) {
      e.printStackTrace();
      return "Erro no envio";
    }
  }
}
