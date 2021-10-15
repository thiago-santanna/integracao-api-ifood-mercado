package com.sigma.ifood.ifoodMercadoApi.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Produto {
	private Long idLoja;	
	private String departamento;
	private String categoria;
	private String subCategoria;
	private String marca;
	private String unidade;
	private String volume;
	private Integer codigoBarra;
	private String nome;	
	private BigDecimal valor;
	private BigDecimal valorPromocao;
	private BigDecimal valorAtacado;
	private BigDecimal valorCompra;
	private BigDecimal quantidadeEstoqueAtual;
	private BigDecimal quantidadeEstoqueMinimo;
	private BigDecimal quantidadeAtacado;	
	private String descricao;
	private Boolean ativo;
	private Integer plu;
	private Boolean validadeProxima;
	private String imageURL;	
	
}
