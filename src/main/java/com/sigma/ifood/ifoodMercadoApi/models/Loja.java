package com.sigma.ifood.ifoodMercadoApi.models;

import lombok.Data;

@Data
public class Loja {
	private Long id;
	private String nome;
	private String cnpj;
	private String status;
	private Endereco enderecoLoja;
	private Rede rede;
}

