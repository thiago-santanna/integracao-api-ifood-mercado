package com.sigma.ifood.domain.models.endereco;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "logradouros")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 10)
	private String cep;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false, length = 100)
	private String bairro;
	
	@Column(nullable = false, length = 100)
	private String cidade;
	
	@Column(nullable = false, length = 10)
	private String ibge;
	
	@Column(nullable = false, length = 2)
	private String ufSigla;
	
	@Column(nullable = false, length = 50)
	private String uf;
	
	private BigDecimal latitude;
	
	private BigDecimal longitude;	
	
	@ManyToOne
	@JoinColumn(nullable = false, referencedColumnName = "id", name = "pais_id")
	private Pais pais;
}
