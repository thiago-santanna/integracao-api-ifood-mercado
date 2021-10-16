package com.sigma.ifood.ifoodMercadoApi.dto;

import lombok.Data;

@Data
public class ErrorProdutoIntegrado {
	private String codigo;
	private String descricao;
	private String message;
	private Integer id;
}