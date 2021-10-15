package com.sigma.ifood.ifoodMercadoApi.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
public class Rede {
	private Long id;
	private String nome;
}
