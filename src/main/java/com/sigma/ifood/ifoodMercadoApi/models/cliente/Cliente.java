package com.sigma.ifood.ifoodMercadoApi.models.cliente;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.sigma.ifood.ifoodMercadoApi.models.endereco.Endereco;

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