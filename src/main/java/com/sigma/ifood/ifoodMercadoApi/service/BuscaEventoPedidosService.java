package com.sigma.ifood.ifoodMercadoApi.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.ifood.exceptions.ApiException;
import com.sigma.ifood.ifoodMercadoApi.models.event.Events;

import reactor.core.publisher.Mono;

@Service
public class BuscaEventoPedidosService {
	@Autowired
	private WebClient webClientMercado;
	
	public List<Events> getEventos(String accessToken) {
		try {
			Mono<Object[]> monoEventos = this.webClientMercado
					.method(HttpMethod.GET)
					.uri("pedido/eventos")
					.header("Authorization", accessToken)
					.retrieve()
					.onStatus(
							HttpStatus::is5xxServerError, clinetResponse -> clinetResponse.bodyToMono(String.class).map(
							body -> new ApiException("Codigo: "+clinetResponse.statusCode().toString() + "\nDetalhes"+ body))
					)			
					.onStatus(
							HttpStatus::is4xxClientError, clinetResponse -> clinetResponse.bodyToMono(String.class).map(
							body -> new ApiException("Codigo: "+clinetResponse.statusCode().toString() + "\nDetalhes"+ body))
					)					
					.bodyToMono(Object[].class)
					.log();
			
			Object[] eventos = monoEventos.block();

			if(eventos.length > 0) {
				ObjectMapper mapper = new ObjectMapper();
				
				return Arrays.stream(eventos)
						.map(object -> mapper.convertValue(object, Events.class))
						.collect(Collectors.toList());				
			}
			
			return null;
		} catch (RuntimeException e) {
			throw new ApiException(e.getMessage(), "getEventos");
		}
	}
}
