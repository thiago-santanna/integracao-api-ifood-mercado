package com.sigma.ifood.ifoodMercadoApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sigma.ifood.ifoodMercadoApi.dto.PedidoVerificado;

import reactor.core.publisher.Mono;

@Service
public class VerificarEventoService {

	@Autowired
	private WebClient webClientMercado;
	
	public void verificaPedido(List<PedidoVerificado> pedidosVerificado, String accessToken) {
		this.webClientMercado
			.post()
			.uri("pedido/eventos/verificado")
			.header("Authorization", accessToken)
			.body(Mono.just(pedidosVerificado), PedidoVerificado.class)
			.retrieve()
			.bodyToMono(Void.class)
			.block();			
	}
}
