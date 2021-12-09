package com.sigma.ifood.ifoodMercadoApi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sigma.ifood.exceptions.ApiException;
import com.sigma.ifood.ifoodMercadoApi.dto.PedidoVerificado;

@Service
public class VerificarEventoService {

	@Autowired
	private WebClient webClientMercado;
	
	public void verificaPedido(List<PedidoVerificado> pedidosVerificado, String accessToken) throws IOException {
		try {
			this.webClientMercado
			.post()
			.uri("pedido/eventos/verificado")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", accessToken)
			.bodyValue(pedidosVerificado)
			.retrieve()
			.onStatus(
					HttpStatus::is5xxServerError, clinetResponse -> clinetResponse.bodyToMono(String.class).map(
					body -> new ApiException("Codigo: "+clinetResponse.statusCode().toString() + "\nDetalhes"+ body))
			)			
			.onStatus(
					HttpStatus::is4xxClientError, clinetResponse -> clinetResponse.bodyToMono(String.class).map(
					body -> new ApiException("Codigo: "+clinetResponse.statusCode().toString() + "\nDetalhes"+ body))
			)
			.bodyToMono(Void.class)
			.block();				
		} catch (RuntimeException e) {
			throw new ApiException(e.getMessage(), "verificaPedido", pedidosVerificado, "flag");
		}		
	}
}
