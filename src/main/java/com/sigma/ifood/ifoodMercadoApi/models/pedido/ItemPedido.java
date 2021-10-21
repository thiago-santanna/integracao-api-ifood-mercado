package com.sigma.ifood.ifoodMercadoApi.models.pedido;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedido {	
	private Long id;
	private String index;
	private String codigo;
	private String codigoLoja;
	private Boolean pesoVariavel;
	private String codigoBarra;
	private String plu;
	private String produto;
	private String observacao;
	private Integer quantidade;
	private Integer quantidade3;
	private BigDecimal valor = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private Boolean indisponivel;
	private Boolean desistencia;
}