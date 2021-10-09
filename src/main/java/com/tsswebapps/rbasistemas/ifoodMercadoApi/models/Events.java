package com.tsswebapps.rbasistemas.ifoodMercadoApi.models;

import lombok.Data;

@Data
public class Events {
	private Long id;
	private String codigoPedido;
	private StatusEvent status;
	private Long idLoja;
}
