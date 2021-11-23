package com.sigma.ifood.domain.models.cliente;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sigma.ifood.domain.models.endereco.EnderecoDomain;

import lombok.Data;

@Data
@Entity
@Table(name = "enderecos_cliente")
public class EnderecoCliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero_endereco_cliente", length = 10)
	private String numero;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private ClienteDomain clienteDomain;
	
	@Embedded
	private EnderecoDomain endereco;	
}
