package com.sigma.ifood.ifoodMercadoApi.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.ifood.ifoodMercadoApi.models.event.Events;

import reactor.core.publisher.Mono;

@Service
public class BuscaEventoPedidosService {
	@Autowired
	private WebClient webClientMercado;
	
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
