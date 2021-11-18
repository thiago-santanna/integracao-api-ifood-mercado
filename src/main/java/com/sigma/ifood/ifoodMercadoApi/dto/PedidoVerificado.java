package com.sigma.ifood.ifoodMercadoApi.dto;

import lombok.Data;

@Data
public class PedidoVerificado {

	private Long id;
	
	public PedidoVerificado(Long id) {
		this.id = id;
	}
}

