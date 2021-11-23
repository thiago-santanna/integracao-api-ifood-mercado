package com.sigma.ifood.domain.models.pedido;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ParceiroDomain {
	@Column(name = "parceiro_codigo_entrega", length = 50)
	private String codigoEntrega;
	
	@Column(name = "parceiro_codigo_pedido", length = 50)
	private String codigoPedido;
}
