package com.sigma.ifood.ifoodMercadoApi.models;

import lombok.Data;

@Data
public class Events {
	private Long id;
	private String codigoPedido;
	private StatusEvent status;
	private Long idLoja;
}
