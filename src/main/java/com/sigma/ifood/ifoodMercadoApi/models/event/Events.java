package com.sigma.ifood.ifoodMercadoApi.models.event;

import com.sigma.ifood.ifoodMercadoApi.models.enums.StatusEvent;

import lombok.Data;

@Data
public class Events {
	private Long id;
	private String codigoPedido;
	private StatusEvent status;
	private Long idLoja;
}
