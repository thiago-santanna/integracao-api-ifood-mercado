package com.sigma.ifood.exceptions;

import java.io.IOException;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClientException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.ifood.ifoodMercadoApi.dto.CredentialsDto;
import com.sigma.ifood.ifoodMercadoApi.dto.PedidoVerificado;
import com.sigma.ifood.ifoodMercadoApi.models.produto.Produto;

public class ApiException extends WebClientException {

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
	
	public ApiException(String message, String metodo, List<PedidoVerificado> pedidosVerificado, String flag) throws IOException {
		this("Erro ao consumir a API => ("+ metodo + ")"+"\nErro: "+ message);
		
		ObjectMapper jkObjectMapper = new ObjectMapper();
		String productJson = jkObjectMapper.writeValueAsString(pedidosVerificado);
		System.out.println(productJson);
	}	
	
	public ApiException(String message, String metodo, CredentialsDto credenciais) throws IOException {
		this("Erro ao consumir a API => ("+ metodo + ")"+"\nErro: "+ message);
		
		ObjectMapper jkObjectMapper = new ObjectMapper();
		String productJson = jkObjectMapper.writeValueAsString(credenciais);
		System.out.println(productJson);
	}
	

	
}
