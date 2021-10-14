package com.sigma.ifood.ifoodMercadoApi.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "rede")
public class Rede {
	@Id
	private Long id;
	private String nome;
}
