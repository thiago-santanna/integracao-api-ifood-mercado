package com.sigma.ifood.ifoodMercadoApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.ifood.ifoodMercadoApi.dto.PedidoVerificado;
import com.sigma.ifood.ifoodMercadoApi.dto.ProdutoIntegrado;
import com.sigma.ifood.ifoodMercadoApi.dto.TokenDto;
import com.sigma.ifood.ifoodMercadoApi.models.Token;
import com.sigma.ifood.ifoodMercadoApi.models.Events;
import com.sigma.ifood.ifoodMercadoApi.models.Pedido;
import com.sigma.ifood.ifoodMercadoApi.models.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public Pedido getPedido(String accessToken, String codigoPedido) {
		Pedido pedido = this.webClientMercado
				.get()
				.uri("pedido/"+codigoPedido)
				.header("Authorization", accessToken)
				.retrieve()
				.bodyToMono(Pedido.class)
				.block();
		
		return pedido;
	}
	
	public ProdutoIntegrado integrarProdutos(String accessToken, List<Produto> produtos) {
		ProdutoIntegrado resultIntegracao = this.webClientMercado
				.post()
				.uri("produtointegracao")
				.header("Authorization", accessToken)
				.body(Mono.just(produtos), Produto.class)
				.retrieve()
				.bodyToMono(ProdutoIntegrado.class)
				.block();
		
		return resultIntegracao;
	}

}
