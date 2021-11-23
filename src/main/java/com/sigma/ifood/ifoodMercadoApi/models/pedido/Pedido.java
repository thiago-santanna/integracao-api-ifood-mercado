package com.sigma.ifood.ifoodMercadoApi.models.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.sigma.ifood.domain.enums.StatusEventoPedido;
import com.sigma.ifood.domain.models.cliente.ClienteDomain;
import com.sigma.ifood.domain.models.cliente.EnderecoCliente;
import com.sigma.ifood.domain.models.endereco.EnderecoDomain;
import com.sigma.ifood.domain.models.loja.LojaDomain;
import com.sigma.ifood.domain.models.pedido.ItemPedidoDomain;
import com.sigma.ifood.domain.models.pedido.ParceiroDomain;
import com.sigma.ifood.domain.models.pedido.PedidoDomain;
import com.sigma.ifood.ifoodMercadoApi.models.cliente.Cliente;
import com.sigma.ifood.ifoodMercadoApi.models.endereco.Endereco;

import lombok.Data;

@Data
public class Pedido {	
	private Long idLoja;
	private Long idCliente;
	private String codigo;
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
	
	public static PedidoDomain toPedidoDomain(Pedido pedido) {
		PedidoDomain pedidoDomain = new PedidoDomain();
		pedidoDomain.setIdLoja(pedido.getIdLoja());
		pedidoDomain.setIdCliente(pedido.getIdCliente());
		pedidoDomain.setCodigo(pedido.getCodigo());
		pedidoDomain.setCodigoLoja(pedido.getCodigoLoja());
		pedidoDomain.setData(pedido.getData());
		pedidoDomain.setHora(pedido.getHora());
		pedidoDomain.setDataHora(pedido.getDataHora());
		pedidoDomain.setAgendamentoDataInicio(pedido.getAgendamentoDataInicio());
		pedidoDomain.setAgendamentoHoraInicio(pedido.getAgendamentoHoraInicio());
		pedidoDomain.setAgendamentoDataFim(pedido.getAgendamentoDataFim());
		pedidoDomain.setAgendamentoHoraFim(pedido.getAgendamentoHoraFim());
		pedidoDomain.setEntrega(pedido.getEntrega());
		pedidoDomain.setCpfNaNota(pedido.getCpfNaNota());
		pedidoDomain.setStatus(pedido.getStatus());
		pedidoDomain.setStatusDescricao(pedido.getStatusDescricao());
		pedidoDomain.setPessoaAutorizadaRecebimento(pedido.getPessoaAutorizadaRecebimento());
		pedidoDomain.setQuantidadeItemUnico(pedido.getQuantidadeItemUnico());
		pedidoDomain.setValorMercado(pedido.getValorMercado());
		pedidoDomain.setValorConveniencia(pedido.getValorConveniencia());
		pedidoDomain.setValorEntrega(pedido.getValorEntrega());
		pedidoDomain.setValorRetirada(pedido.getValorRetirada());
		pedidoDomain.setValorTroco(pedido.getValorTroco());
		pedidoDomain.setValorDesconto(pedido.getValorDesconto());
		pedidoDomain.setValorTotal(pedido.getValorTotal());
		pedidoDomain.setValorCorrigido(pedido.getValorCorrigido());
		pedidoDomain.setPlataforma(pedido.getPlataforma());
		
		ParceiroDomain parceiroDomain = new ParceiroDomain();
		parceiroDomain.setCodigoEntrega(pedido.getParceiro().getCodigoEntrega());
		parceiroDomain.setCodigoPedido(pedido.getParceiro().getCodigoPedido());
		pedidoDomain.setParceiroDomain(parceiroDomain);
		
		EnderecoDomain enderecoDomain = new EnderecoDomain();
		enderecoDomain.setIdEnderecoEntrega(pedido.getEnderecoEntrega().getId());
		enderecoDomain.setCep(pedido.getEnderecoEntrega().getCep());
		enderecoDomain.setLogradouro(pedido.getEnderecoEntrega().getLogradouro());
		enderecoDomain.setNumero(pedido.getEnderecoEntrega().getNumero());
		enderecoDomain.setComplemento(pedido.getEnderecoEntrega().getComplemento());
		enderecoDomain.setBairro(pedido.getEnderecoEntrega().getBairro());
		enderecoDomain.setCidade(pedido.getEnderecoEntrega().getCidade());
		enderecoDomain.setUf(pedido.getEnderecoEntrega().getUf());
		enderecoDomain.setEstado(pedido.getEnderecoEntrega().getEstado());
		enderecoDomain.setLatitude(pedido.getEnderecoEntrega().getLatitude());
		enderecoDomain.setLongitude(pedido.getEnderecoEntrega().getLongitude());
		pedidoDomain.setEnderecoEntrega(enderecoDomain);
		
		LojaDomain lojaDomain = new LojaDomain();
		lojaDomain.setId(pedido.getLoja().getId());
		lojaDomain.setNome(pedido.getLoja().getNome());
		lojaDomain.setCnpj(pedido.getLoja().getCnpj());
		lojaDomain.setStatus(pedido.getLoja().getStatus());
		lojaDomain.setNumero(pedido.getLoja().getEnderecoLoja().getNumero());
		lojaDomain.setCep(pedido.getLoja().getEnderecoLoja().getCep());
		lojaDomain.setLogradouro(pedido.getLoja().getEnderecoLoja().getLogradouro());
		lojaDomain.setBairro(pedido.getLoja().getEnderecoLoja().getBairro());
		lojaDomain.setCidade(pedido.getLoja().getEnderecoLoja().getCidade());
		lojaDomain.setUf(pedido.getLoja().getEnderecoLoja().getUf());
		lojaDomain.setEstado(pedido.getLoja().getEnderecoLoja().getEstado());
		lojaDomain.setRedeId(pedido.getLoja().getRede().getId());
		lojaDomain.setRedeNome(pedido.getLoja().getRede().getNome());
		pedidoDomain.setLojaDomain(lojaDomain);		
		
		ClienteDomain clienteDomian = new ClienteDomain();
		clienteDomian.setId(pedido.getCliente().getId());
		clienteDomian.setNome(pedido.getCliente().getNome());
		clienteDomian.setEmail(pedido.getCliente().getEmail());
		clienteDomian.setCpf(pedido.getCliente().getCpf());
		clienteDomian.setTipo(pedido.getCliente().getTipo());
		clienteDomian.setPublicidadeEmail(pedido.getCliente().getPublicidadeEmail());
		clienteDomian.setPublicidadeSms(pedido.getCliente().getPublicidadeSms());
		clienteDomian.setTelefoneCelular(pedido.getCliente().getTelefoneCelular());	
		
		List<EnderecoCliente> enderecosCliente = new ArrayList<>();						
		for (Endereco endereco : pedido.getCliente().getEnderecos()) {
			EnderecoDomain enderecoClienteDomain = new EnderecoDomain();
			enderecoDomain.setIdEnderecoEntrega(endereco.getId());
			enderecoDomain.setCep(endereco.getCep());
			enderecoDomain.setLogradouro(endereco.getLogradouro());
			enderecoDomain.setNumero(endereco.getNumero());
			enderecoDomain.setComplemento(endereco.getComplemento());
			enderecoDomain.setBairro(endereco.getBairro());
			enderecoDomain.setCidade(endereco.getCidade());
			enderecoDomain.setUf(endereco.getUf());
			enderecoDomain.setEstado(endereco.getEstado());
			enderecoDomain.setLatitude(endereco.getLatitude());
			enderecoDomain.setLongitude(endereco.getLongitude());
			
			EnderecoCliente enderecoCliente = new EnderecoCliente();
			enderecoCliente.setNumero(endereco.getNumero());
			enderecoCliente.setClienteDomain(clienteDomian);
			enderecoCliente.setEndereco(enderecoClienteDomain);
			
			enderecosCliente.add(enderecoCliente);			
		}
		clienteDomian.setEnderecos(enderecosCliente);		
		pedidoDomain.setClienteDomain(clienteDomian);
		
		List<ItemPedidoDomain> itensPedidoDomain = new ArrayList<>();		
		for( ItemPedido itemPedido : pedido.getItems() ) {
			ItemPedidoDomain itemPedidoDomain = new ItemPedidoDomain();
			itemPedidoDomain.setId(itemPedido.getId());
			itemPedidoDomain.setIndex(itemPedido.getIndex());
			itemPedidoDomain.setCodigo(itemPedido.getCodigo());
			itemPedidoDomain.setCodigoLoja(itemPedido.getCodigoLoja());
			itemPedidoDomain.setPesoVariavel(itemPedido.getPesoVariavel());
			itemPedidoDomain.setCodigoBarra(itemPedido.getCodigoBarra());
			itemPedidoDomain.setPlu(itemPedido.getPlu());
			itemPedidoDomain.setProduto(itemPedido.getProduto());
			itemPedidoDomain.setObservacao(itemPedido.getObservacao());
			itemPedidoDomain.setQuantidade(itemPedido.getQuantidade());
			itemPedidoDomain.setQuantidade3(itemPedido.getQuantidade3());
			itemPedidoDomain.setValor(itemPedido.getValor());
			itemPedidoDomain.setValorTotal(itemPedido.getValorTotal());
			itemPedidoDomain.setIndisponivel(itemPedido.getIndisponivel());
			itemPedidoDomain.setDesistencia(itemPedido.getDesistencia());
			itemPedidoDomain.setPedidoDomain(pedidoDomain);
			itensPedidoDomain.add(itemPedidoDomain);
		}
		pedidoDomain.setItems(itensPedidoDomain);

		return pedidoDomain;
	}	
}	  
