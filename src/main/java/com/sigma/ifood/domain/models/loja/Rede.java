package com.sigma.ifood.domain.models.loja;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Rede {
	@Column(name = "rede_id")
	private Long id;
	
	@Column(name = "rede_nome", length = 150)
	private String nome;
}
