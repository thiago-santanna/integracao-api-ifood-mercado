package com.sigma.ifood.ifoodMercadoApi.models.endereco;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Endereco {
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
