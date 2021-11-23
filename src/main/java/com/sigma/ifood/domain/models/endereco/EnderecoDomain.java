package com.sigma.ifood.domain.models.endereco;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class EnderecoDomain {
	
	private Long idEnderecoEntrega;
	
	@Column(nullable = false, length = 10)
	private String cep;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(length = 10)
	private String numero;
	
	@Column(nullable = false, length = 100)
	private String complemento;
	
	@Column(nullable = false, length = 100)
	private String bairro;	
	
	@Column(nullable = false, length = 100)
	private String cidade;
	
	@Column(length = 10)
	private String ibge;
	
	@Column(nullable = false, length = 2)
	private String uf;
	
	@Column(nullable = false, length = 50)
	private String estado;
	
	private BigDecimal latitude;
	
	private BigDecimal longitude;
}
