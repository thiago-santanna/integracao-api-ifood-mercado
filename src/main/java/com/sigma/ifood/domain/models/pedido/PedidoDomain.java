package com.sigma.ifood.domain.models.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sigma.ifood.domain.enums.StatusEventoPedido;
import com.sigma.ifood.domain.models.cliente.ClienteDomain;
import com.sigma.ifood.domain.models.endereco.EnderecoDomain;
import com.sigma.ifood.domain.models.loja.LojaDomain;

import lombok.Data;

@Data
@Entity
@Table(name = "pedidos_integracao")
public class PedidoDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private Long idLoja;
	private Long idCliente;
	
	@Column(length = 50)
	private String codigo;
	
	@Column(length = 50)
	private String codigoLoja;	
	
	private LocalDate data;
	private LocalTime hora;
	private LocalDateTime dataHora;
	private LocalDate agendamentoDataInicio;
	private LocalTime agendamentoHoraInicio;
	private LocalDate agendamentoDataFim;
	private LocalTime agendamentoHoraFim;
	private Boolean entrega;
	private Boolean cpfNaNota;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private StatusEventoPedido status;
	
	@Column(length = 30)
	private String statusDescricao;
	
	@Column(length = 100)
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
	
	@Embedded
	private ParceiroDomain parceiroDomain;
	
	@Column(length = 100)
	private String plataforma;
	
	@Embedded
	private EnderecoDomain enderecoEntrega;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loja_id", referencedColumnName = "id")
	private LojaDomain lojaDomain;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private ClienteDomain clienteDomain;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pedidoDomain", cascade = CascadeType.ALL)
	private List<ItemPedidoDomain> items = new ArrayList<>();	
}
