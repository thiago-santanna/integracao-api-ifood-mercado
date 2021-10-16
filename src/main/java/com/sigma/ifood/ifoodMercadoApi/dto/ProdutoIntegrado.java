package com.sigma.ifood.ifoodMercadoApi.dto;

import lombok.Data;

@Data
public class ProdutoIntegrado {
	private Long idLoja;
	private String success;
	private ErrorProdutoIntegrado error;
}