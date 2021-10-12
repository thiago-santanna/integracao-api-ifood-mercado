package com.sigma.ifood.ifoodMercadoApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.ifood.ifoodMercadoApi.dto.TokenDto;
import com.sigma.ifood.ifoodMercadoApi.models.Token;
import com.sigma.ifood.ifoodMercadoApi.models.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IfoodMercadoService {
	
	@Autowired
	private WebClient webClientMercado;

	private static List<Token> tokensAtivos = new ArrayList();
		
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
	
	public List<Events> getEventos(String accessToken) {
		Mono<Object[]> monoEventos = this.webClientMercado
				.method(HttpMethod.GET)
				.uri("pedido/eventos")
				.header("Authorization", accessToken)
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
