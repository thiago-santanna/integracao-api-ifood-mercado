package com.sigma.ifood.domain.models.loja;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sigma.ifood.domain.models.endereco.Endereco;

import lombok.Data;

@Data
@Entity
@Table(name = "loja")
public class Loja {
	@Id
	private Long id;
	
	@Column(length = 100)
	private String nome;
	
	@Column(length = 20)
	private String cnpj;
	
	@Column(length = 20)
	private String status;
	
	@Column(name = "numero_enredeco_loja", length = 10)
	private String numeroEndereco;
	
	@OneToOne
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco enderecoLoja;
	
	@Embedded
	private Rede rede;
}
