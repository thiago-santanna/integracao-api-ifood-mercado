package com.tsswebapps.rbasistemas.ifoodMercadoApi.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsswebapps.rbasistemas.ifoodMercadoApi.dto.TokenDto;
import com.tsswebapps.rbasistemas.ifoodMercadoApi.models.Events;
import com.tsswebapps.rbasistemas.ifoodMercadoApi.models.Token;

import reactor.core.publisher.Mono;

@Service
public class IfoodMercadoService {
	
	@Autowired
	private WebClient webClientMercado;
	
	public Token getToken(TokenDto credenciais) {	
		Mono<Token> monoToken = this.webClientMercado
				.method(HttpMethod.POST)
				.uri("oauth/token")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(credenciais), TokenDto.class) 
				.retrieve()
				.bodyToMono(Token.class);
		
		return monoToken.block();
	}
	
	public List<Events> getEventos() {
		Mono<Object[]> monoEventos = this.webClientMercado
				.method(HttpMethod.GET)
				.uri("pedido/eventos")
				.header("Authorization", "")
				.retrieve()
				.bodyToMono(Object[].class)
				.log();
		
		Object[] eventos = monoEventos.block();
		
		ObjectMapper mapper = new ObjectMapper();
		
		return Arrays.stream(eventos)
				  .map(object -> mapper.convertValue(object, Events.class))
				  .collect(Collectors.toList());
	}

}
