package com.sigma.ifood.ifoodMercadoApi.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "loja")
public class Loja {
	@Id
	private Long id;
	private String nome;
	private String cnpj;
	private String status;
	@OneToOne
	@JoinColumn(name = "endereco_loja_id", referencedColumnName = "id")
	private EnderecoLoja enderecoLoja;
	@OneToOne
	@JoinColumn(name = "rede_id", referencedColumnName = "id")
	private Rede rede;
}

