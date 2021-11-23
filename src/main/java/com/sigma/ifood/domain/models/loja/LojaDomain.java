package com.sigma.ifood.domain.models.loja;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "loja")
public class LojaDomain {
	@Id
	private Long id;
	
	@Column(length = 100)
	private String nome;
	
	@Column(length = 20)
	private String cnpj;
	
	@Column(length = 20)
	private String status;
	
	@Column(name = "numero_enredeco_loja", length = 10)
	private String numero;
	
	@Column(nullable = false, length = 10)
	private String cep;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false, length = 100)
	private String bairro;	
	
	@Column(nullable = false, length = 100)
	private String cidade;
	
	@Column(nullable = false, length = 2)
	private String uf;
	
	@Column(nullable = false, length = 50)
	private String estado;	
	
	@Column(name = "rede_id")
	private Long redeId;
	
	@Column(name = "rede_nome", length = 150)
	private String redeNome;
}
