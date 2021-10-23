package com.sigma.ifood.ifoodMercadoApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sigma.ifood.ifoodMercadoApi.dto.ProdutoIntegrado;
import com.sigma.ifood.ifoodMercadoApi.models.produto.Produto;

import reactor.core.publisher.Mono;

@Service
public class IntegrarProdutoService {
	
	@Autowired
	private WebClient webClientMercado;

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
