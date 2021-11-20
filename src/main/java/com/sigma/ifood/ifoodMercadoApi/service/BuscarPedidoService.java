package com.sigma.ifood.ifoodMercadoApi.service;

import org.springframework.beans.factory.annotation.Autowired;
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
					.bodyToMono(Pedido.class)
					.block();
			
			return pedido;			
		} catch (RuntimeException e) {
			throw new ApiException(e.getMessage(), "getPedido");
		}
	}
		
}
