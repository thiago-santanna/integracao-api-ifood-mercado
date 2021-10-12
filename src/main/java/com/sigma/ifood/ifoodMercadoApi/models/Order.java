package com.sigma.ifood.ifoodMercadoApi.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
	private Long idLoja;
	private Long idCliente;
	private String codigo;
	private String codigoLoja;
	private String data;
	private String hora;
	private String dataHora;
	private String agendamentoDataInicio;
	private String agendamentoHoraInicio;
	private String agendamentoDataFim;
	private String agendamentoHoraFim;
	private Boolean entrega;
	private Boolean cpfNaNota;
	private String status;
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
	private EnderecoEntrega enderecoEntrega;
	private Loja loja;
	private Cliente cliente;
	private List<Item> items = new ArrayList<>();
	

}	  
