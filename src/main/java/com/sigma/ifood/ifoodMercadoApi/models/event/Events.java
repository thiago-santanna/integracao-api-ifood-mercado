package com.sigma.ifood.ifoodMercadoApi.models.event;

import com.sigma.ifood.ifoodMercadoApi.models.enums.StatusEventoPedido;

import lombok.Data;

@Data
public class Events {
	private Long id;
	private String codigoPedido;
	private StatusEventoPedido status;
	private Integer idLoja;
}
