package com.sigma.ifood.domain.models.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idCliBase;
	
	@Column(length = 150)
	private String nome;
	
	@Column(length = 100)
	private String email;
	
	@Column(length = 20)
	private String cpf;
	
	@Column(length = 50)
	private String tipo;
	
	private Boolean publicidadeEmail;
	private Boolean publicidadeSms;
	
	@Column(length = 50)
	private String telefoneCelular;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<EnderecoCliente> enderecosCliente = new ArrayList<>();
}
