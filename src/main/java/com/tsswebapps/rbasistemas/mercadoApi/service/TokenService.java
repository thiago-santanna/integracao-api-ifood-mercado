package com.tsswebapps.rbasistemas.mercadoApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tsswebapps.rbasistemas.mercadoApi.dto.TokenDto;
import com.tsswebapps.rbasistemas.mercadoApi.models.Token;

import reactor.core.publisher.Mono;

@Service
public class TokenService {
	
	@Autowired
	private WebClient webClientMercado;
	
	public Token getToken(TokenDto credenciais) {	
		Mono<Token> monoToken = this.webClientMercado .method(HttpMethod.POST)
				  .uri("oauth/token") .contentType(MediaType.APPLICATION_JSON)
				  .body(Mono.just(credenciais), TokenDto.class) 
				  .retrieve()
				  .bodyToMono(Token.class);
		
		return monoToken.block();
	}

}
