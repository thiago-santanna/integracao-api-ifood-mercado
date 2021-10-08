package com.tsswebapps.rbasistemas.mercadoApi.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cliente {
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String tipo;
	private Boolean publicidadeEmail;
	private Boolean publicidadeSms;
	private String telefoneCelular;
	private List<Endereco> enderecos = new ArrayList<>();	
}