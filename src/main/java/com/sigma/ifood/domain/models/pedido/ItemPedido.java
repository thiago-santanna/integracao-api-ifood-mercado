package com.sigma.ifood.domain.models.pedido;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
	
	@Id
	private Long id;
	private Long idItemPedido;
	
	@Column(name = "valueIndex")
	private String index;
	
	@Column(length = 50)
	private String codigo;
	
	@Column(length = 50)
	private String codigoLoja;
	
	private Boolean pesoVariavel;
	
	@Column(length = 20)
	private String codigoBarra;
	
	@Column(length = 50)
	private String plu;
	
	private String produto;
	private String observacao;
	private Integer quantidade;
	private Integer quantidade3;
	private BigDecimal valor = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private Boolean indisponivel;
	private Boolean desistencia;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", referencedColumnName = "id")
	private Pedido pedido;
}
