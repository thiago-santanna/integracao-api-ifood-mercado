package com.sigma.ifood.ifoodMercadoApi.models;

import lombok.Data;

@Data
public class Endereco {
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	private String estado;
	private String cep;

}