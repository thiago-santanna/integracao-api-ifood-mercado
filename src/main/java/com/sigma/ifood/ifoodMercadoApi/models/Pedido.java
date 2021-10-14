package com.sigma.ifood.ifoodMercadoApi.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idLoja;
	private Long idCliente;
	private String codigo;
	private String codigoLoja;
	private LocalDateTime data;
	private LocalTime hora;
	private LocalDateTime dataHora;
	private LocalDateTime agendamentoDataInicio;
	private LocalTime agendamentoHoraInicio;
	private LocalDateTime agendamentoDataFim;
	private LocalTime agendamentoHoraFim;
	private Boolean entrega;
	private Boolean cpfNaNota;
	
	@Enumerated(EnumType.STRING)
	private StatusEvent status;
	
	private String statusDescricao;
	private String pessoaAutorizadaRecebimento;
	private Integer quantidadeItemUnico;
	private BigDecimal valorMercado;
	private BigDecimal valorConveniencia;
	private BigDecimal valorEntrega;
	private BigDecimal valorRetirada;
	private BigDecimal valorTroco;
	private BigDecimal valorDesconto;
	private BigDecimal valorTotal;
	private BigDecimal valorCorrigido;
	private String plataforma;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id", name = "parceiro_id")
	private Parceiro parceiro;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id", name = "endereco_entrega_id")
	private EnderecoEntrega enderecoEntrega;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id", name = "loja_id")
	private Loja loja;
	
	//private Cliente cliente;
	//private List<ItemPedido> itemPedidos = new ArrayList<>();
	

}	  
