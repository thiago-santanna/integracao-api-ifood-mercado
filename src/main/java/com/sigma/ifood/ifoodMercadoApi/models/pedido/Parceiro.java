package com.sigma.ifood.ifoodMercadoApi.models.pedido;

import lombok.Data;

@Data
public class Parceiro {
	private Long id;
	private String codigoEntrega;
	private String codigoPedido;
}
