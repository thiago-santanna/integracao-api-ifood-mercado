package com.sigma.ifood.schedule;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sigma.ifood.domain.assemblers.ProductDomainAssembler;
import com.sigma.ifood.domain.models.pedido.Eventos;
import com.sigma.ifood.domain.models.pedido.PedidoDomain;
import com.sigma.ifood.domain.models.produto.ProdutoDomain;
import com.sigma.ifood.domain.service.EventosService;
import com.sigma.ifood.domain.service.PedidoService;
import com.sigma.ifood.domain.service.ProdutoDomainService;
import com.sigma.ifood.exceptions.ApiException;
import com.sigma.ifood.ifoodMercadoApi.dto.PedidoVerificado;
import com.sigma.ifood.ifoodMercadoApi.models.event.Events;
import com.sigma.ifood.ifoodMercadoApi.models.pedido.Pedido;
import com.sigma.ifood.ifoodMercadoApi.models.produto.Produto;
import com.sigma.ifood.ifoodMercadoApi.service.BuscaEventoPedidosService;
import com.sigma.ifood.ifoodMercadoApi.service.BuscarPedidoService;
import com.sigma.ifood.ifoodMercadoApi.service.BuscarTokenValido;
import com.sigma.ifood.ifoodMercadoApi.service.IntegrarProdutoService;
import com.sigma.ifood.ifoodMercadoApi.service.VerificarEventoService;

@Service
@EnableScheduling
@EnableAsync
public class AgendamentoService {
	@Autowired
	private BuscarTokenValido buscarToken;

	@Autowired
	private BuscaEventoPedidosService buscarEventosService;

	@Autowired
	private EventosService eventosService;

	@Autowired
	private ProdutoDomainService produtoDomainService;

	@Autowired
	private ProductDomainAssembler productDomainAssembler;

	@Autowired
	private IntegrarProdutoService integrarProdutoService;

	@Autowired
	private BuscarPedidoService buscarPedidoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private VerificarEventoService verificarEventService;
	
	private String accessToken;
	
	@Async
	@Scheduled(fixedDelayString = "${fixeddelay.token}", initialDelayString = "${initialdelay.busca.token}")
	public void obterTokenSchedule() throws IOException {
		this.accessToken = buscarToken.getTokenValid();	
	}

	@Async
	@Scheduled(fixedDelayString = "${fixeddelay.evento}", initialDelayString = "${initialdelay.busca.evento}")
	public void verificarEventos() throws IOException {
		try {
			
			if(accessToken != null) {
				// Pegando lista de eventos
				List<Events> eventos = buscarEventosService
						.getEventos(accessToken);
				
				if (eventos != null) {
					ObjectMapper mapper = new ObjectMapper();
					mapper.registerModule(new JavaTimeModule());
					List<Eventos> domainEventos = eventos.stream()
							.map(evt -> mapper.convertValue(evt, Eventos.class))
							.collect(Collectors.toList());
					
					// Salvando a lista no banco de dados
					List<Eventos> EventsSaved = eventosService.salvar(domainEventos);
					
					//Salvando pedido
					for (Events event : eventos) {
						Pedido pedido = buscarPedidoService.getPedido(accessToken, event.getCodigoPedido());						
						PedidoDomain pedidoDomain = Pedido.toPedidoDomain(pedido);
						pedidoService.salvar(pedidoDomain);
					}
					
					// Verificando/Limpando a lista de eventos da api
					if (!EventsSaved.isEmpty()) {
						List<PedidoVerificado> pedidosVerificados = EventsSaved.stream()
								.map(evt -> new PedidoVerificado(evt.getId()))
								.collect(Collectors.toList());
						verificarEventService
						.verificaPedido(pedidosVerificados, accessToken);
					}
				}	
			}
		} catch (ApiException e) {
			System.out.println(e.getMessage());
		}
	}

	@Async
	@Scheduled(fixedDelayString = "${fixeddelay.produto}" , initialDelayString = "${initialdelay.integra.produto}" )
	public void integrarProdutos() throws IOException {
		try {
			
			if(accessToken != null) {
				List<ProdutoDomain> lisOfProductIntegrable = produtoDomainService.lisOfProductIntegrable();
				List<Produto> produtos = productDomainAssembler.toProdutoIfoodMercado(lisOfProductIntegrable);
				
				integrarProdutoService.integrarProdutos(accessToken, produtos);
				for (ProdutoDomain produto : lisOfProductIntegrable) {
					produto.setDataUltimaItegracao(LocalDateTime.now());
					produto.setIntegrar(false);
				}
				produtoDomainService.updatedProductsIntegrated(lisOfProductIntegrable);				
			}

		} catch (ApiException e) {
			System.out.println(e.getMessage());
		}
	}

}
