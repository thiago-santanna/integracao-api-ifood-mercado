package com.tsswebapps.rbasistemas.ifoodMercadoApi.models;

import lombok.Data;

@Data
public class Loja {
	private Long id;
	private String nome;
	private String cnpj;
	private String status;
	private Endereco endereco;
	private Rede rede;
}

