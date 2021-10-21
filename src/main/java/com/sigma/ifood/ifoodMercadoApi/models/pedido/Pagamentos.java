package com.sigma.ifood.ifoodMercadoApi.models.pedido;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Pagamentos {
	private Long id;
	private String nome;
	private BigDecimal valor;
	private String tipo;
	private String transacoes; //rever na documentação
}
