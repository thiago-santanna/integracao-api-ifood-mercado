package com.tsswebapps.rbasistemas.ifoodMercadoApi.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EnderecoEntrega {
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
