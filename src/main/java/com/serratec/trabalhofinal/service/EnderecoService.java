package com.serratec.trabalhofinal.service;

import com.serratec.trabalhofinal.model.Endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.codec.http.HttpMethod;
import reactor.core.publisher.Mono;

@Service
public class EnderecoService {
    
    @Autowired
    private WebClient _cepWebCliente;


    public Endereco ObterEnderecoPorCep(String cep){

        Mono<Endereco> monoEndereco = this._cepWebCliente
            .method(org.springframework.http.HttpMethod.GET)
            .uri("/{cep}/json", cep)
            .retrieve()
            .bodyToMono(Endereco.class);
        
            var endereco = monoEndereco.block();

        return endereco;
    }
}