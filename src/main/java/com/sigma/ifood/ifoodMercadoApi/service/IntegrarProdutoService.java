package com.sigma.ifood.ifoodMercadoApi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sigma.ifood.exceptions.ApiException;
import com.sigma.ifood.ifoodMercadoApi.models.produto.Produto;

import lombok.extern.slf4j.Slf4j;

@Service
public class IntegrarProdutoService {
	
	@Autowired
	private WebClient webClientMercado;

	public void integrarProdutos(String accessToken, List<Produto> produtos) throws IOException {
		try {
			this.webClientMercado
			.post()
			.uri("produtointegracao?reset=false")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", accessToken)
			.bodyValue(produtos)
			.retrieve()	
			.onStatus(
					HttpStatus::is5xxServerError, clinetResponse -> clinetResponse.bodyToMono(String.class).map(
					body -> new ApiException("Codigo: "+clinetResponse.statusCode().toString() + "\nDetalhes"+ body))
			)			
			.onStatus(
					HttpStatus::is4xxClientError, clinetResponse -> clinetResponse.bodyToMono(String.class).map(
					body -> new ApiException("Codigo: "+clinetResponse.statusCode().toString() + "\nDetalhes"+ body))
			)
			.bodyToMono(String.class)
			.block();			
		} catch (RuntimeException e) {
			throw new ApiException(e.getMessage(), "integrarProdutos", produtos);
		}
	}

}
