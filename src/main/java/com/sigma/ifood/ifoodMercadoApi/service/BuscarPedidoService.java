package com.sigma.ifood.ifoodMercadoApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sigma.ifood.exceptions.ApiException;
import com.sigma.ifood.ifoodMercadoApi.models.pedido.Pedido;

@Service
public class BuscarPedidoService {

	@Autowired
	private WebClient webClientMercado;
	
	public Pedido getPedido(String accessToken, String codigoPedido) {
		try {
			Pedido pedido = this.webClientMercado
					.get()
					.uri("pedido/"+codigoPedido)
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
					.bodyToMono(Pedido.class)
					.block();
			//System.out.println(pedido);
			return pedido;			
		} catch (RuntimeException e) {
			throw new ApiException(e.getMessage(), "getPedido");
		}
	}
		
}
