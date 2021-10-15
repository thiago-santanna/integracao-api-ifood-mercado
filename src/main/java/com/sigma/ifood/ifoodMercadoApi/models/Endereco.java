package com.sigma.ifood.ifoodMercadoApi.models;

import lombok.Data;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "endereco_entrega")
public class Endereco {
	@Id
	private Long id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String estado;
	private String cep;
	private BigDecimal latitude;
	private BigDecimal longitude;
}
