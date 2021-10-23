package com.sigma.ifood.domain.models.endereco;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "pais")
public class Pais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50)
	private String nome;
}
