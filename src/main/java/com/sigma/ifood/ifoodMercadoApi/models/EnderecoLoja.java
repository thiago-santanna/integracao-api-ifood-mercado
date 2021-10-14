package com.sigma.ifood.ifoodMercadoApi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "endereco_loja")
public class EnderecoLoja {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	private String estado;
	private String cep;

}