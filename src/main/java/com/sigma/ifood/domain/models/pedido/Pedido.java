package com.sigma.ifood.domain.models.pedido;

import java.math.BigDecimal;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sigma.ifood.domain.enums.StatusEventoPedido;
import com.sigma.ifood.domain.models.cliente.Cliente;
import com.sigma.ifood.domain.models.endereco.Endereco;
import com.sigma.ifood.domain.models.loja.Loja;

import lombok.Data;

@Data
@Entity
@Table(name = "pedidos_integracao")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idLoja;
	private Long idCliente;
	
	@Column(length = 50)
	private String codigo;
	
	@Column(length = 50)
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
	private Parceiro parceiro;
	
	@Column(length = 100)
	private String plataforma;
		
	@Column(name = "numero_endereco_entrega", length = 10)
	private String numero;
	
	@OneToOne
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco enderecoEntrega;
	
	@OneToOne
	@JoinColumn(name = "loja_id", referencedColumnName = "id")
	private Loja loja;
	
	@OneToOne
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> items = new ArrayList<>();	
}
