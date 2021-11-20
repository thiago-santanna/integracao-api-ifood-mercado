package com.sigma.ifood.ifoodMercadoApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sigma.ifood.exceptions.ApiException;
import com.sigma.ifood.ifoodMercadoApi.models.produto.Produto;

@Service
public class IntegrarProdutoService {
	
	@Autowired
	private WebClient webClientMercado;

	public void integrarProdutos(String accessToken, List<Produto> produtos) {
		try {
			this.webClientMercado
			.post()
			.uri("produtointegracao")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", accessToken)
			.bodyValue(produtos)
			.retrieve()
			.bodyToMono(Void.class)
			.block();			
		} catch (RuntimeException e) {
			throw new ApiException(e.getMessage(), "integrarProdutos");
		}
	}

}
