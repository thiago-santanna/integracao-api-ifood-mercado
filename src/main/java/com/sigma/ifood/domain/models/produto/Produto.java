package com.sigma.ifood.domain.models.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "produtos_integracao")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean integrar = true;
	private LocalDateTime dataUltimaItegracao;	
	//Campos para compor o json, abaixo
	private Long idLoja;	
	
	@Column(length = 100)
	private String departamento;
	
	@Column(length = 100)
	private String categoria;
	
	@Column(length = 100)
	private String subCategoria;
	
	@Column(length = 100)
	private String marca;
	
	@Column(length = 100)
	private String unidade;
	
	@Column(length = 100)
	private String volume;
	
	
	private Integer codigoBarra;
	
	@Column(length = 150)
	private String nome;
	
	private BigDecimal valor;
	private BigDecimal valorPromocao;
	private BigDecimal valorAtacado;
	private BigDecimal valorCompra;
	private BigDecimal quantidadeEstoqueAtual;
	private BigDecimal quantidadeEstoqueMinimo;
	private BigDecimal quantidadeAtacado;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	private Boolean ativo;
	private Integer plu;
	private Boolean validadeProxima;
	
	@Column(length = 150)
	private String imageURL;
}
