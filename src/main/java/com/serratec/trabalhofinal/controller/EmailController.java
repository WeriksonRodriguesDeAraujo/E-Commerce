package com.serratec.trabalhofinal.controller;

import java.util.Arrays;
import java.util.List;

import com.serratec.trabalhofinal.model.Pedido;
import com.serratec.trabalhofinal.model.Produto;
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
  public String enviarEmailPedido(Pedido pedido) {			
	  var email = new MensagemEmail(
			  "Informações do Pedido",
			  "Data de Entrega: 17/06/2021| " +
			  "Produtos: " + printarNomeDeProdutos(pedido.getProdutos()) +"| Quantidades: " + pedido.getNumero() + "| Valor Final do Pedido: " + pedido.getValorTotal(),
			  "E-Commerce <serratecdev@gmail.com>",
        Arrays.asList(pedido.getCliente().getEmail()));
	  try {
		  
	  mailler.enviar(email);
	  return "Email enviado";
	  
	  } catch (Exception e) {
		  
		  e.printStackTrace();
		  
		  return "Erro no envio";
	  }
  }
  
  @PostMapping
  public String enviarEmailDoPedido() {			
    
    var email = new MensagemEmail(
			  "Informações do Pedido",
			  "Data de Entrega: 17/06/2021 " + _servicoPedido.obterTodos().size(),
        "E-Commerce <serratecdev@gmail.com>",
        Arrays.asList("Teste <serratecdev@gmail.com>"));

    try {
      mailler.enviar(email);
      return "Email enviado";

    } catch (Exception e) {
      e.printStackTrace();
      return "Erro no envio";
    }
  }

  public String enviarEmail(@RequestBody MensagemEmail email) {			
    try {
      mailler.enviar(email);
      return "Email enviado";

    } catch (Exception e) {
      e.printStackTrace();
      return "Erro no envio";
    }
  }
  
  public String printarNomeDeProdutos(List<Produto> produto) {
	  for (Produto nomeProduto : produto) {
	  var nome = nomeProduto.getNome();
	  return nome;
	}
	  return "";
  }
  
}
