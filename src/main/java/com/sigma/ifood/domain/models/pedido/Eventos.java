package com.sigma.ifood.domain.models.pedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sigma.ifood.domain.enums.StatusEventoPedido;

import lombok.Data;

@Data
@Entity
@Table(name = "eventos_pedidos_loja")
public class Eventos {
	@Id
	private Long id;
	
	@Column(length = 50)
	private String codigoPedido;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private StatusEventoPedido status;
	
	private Integer idLoja;
}
