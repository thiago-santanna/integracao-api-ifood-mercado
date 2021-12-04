package com.sigma.ifood.exceptions;

import java.io.IOException;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.ifood.ifoodMercadoApi.models.produto.Produto;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(String message, String metodo) {
		this("Erro ao consumir a API => ("+ metodo + ")"+"\nErro: "+ message);
	}
	
	public ApiException(String message, String metodo, List<Produto> produtos) throws IOException {
		this("Erro ao consumir a API => ("+ metodo + ")"+"\nErro: "+ message);
		
		ObjectMapper jkObjectMapper = new ObjectMapper();
		String productJson = jkObjectMapper.writeValueAsString(produtos);
		System.out.println(productJson);
	}
}
