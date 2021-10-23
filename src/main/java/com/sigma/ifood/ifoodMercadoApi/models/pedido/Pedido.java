package com.sigma.ifood.ifoodMercadoApi.models.pedido;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.sigma.ifood.ifoodMercadoApi.models.cliente.Cliente;
import com.sigma.ifood.ifoodMercadoApi.models.endereco.Endereco;
import com.sigma.ifood.ifoodMercadoApi.models.enums.StatusEventoPedido;

@Data
public class Pedido {	
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
	private StatusEventoPedido status;	
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
	private Parceiro parceiro;
	private String plataforma;	
	private Endereco enderecoEntrega;
	private Loja loja;
	private Cliente cliente;
	private List<ItemPedido> items = new ArrayList<>();
}	  
