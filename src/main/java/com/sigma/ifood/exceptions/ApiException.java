package com.sigma.ifood.exceptions;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(String message, String metodo) {
		this("Erro ao consumir a API => ("+ metodo + ")"+"\nErro: "+ message);
	}
}
