package com.sigma.ifood.schedule;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.ifood.domain.assemblers.ProductDomainAssembler;
import com.sigma.ifood.domain.models.pedido.Eventos;
import com.sigma.ifood.domain.models.produto.ProdutoDomain;
import com.sigma.ifood.domain.service.EventosService;
import com.sigma.ifood.domain.service.ProdutoDomainService;
import com.sigma.ifood.ifoodMercadoApi.models.event.Events;
import com.sigma.ifood.ifoodMercadoApi.models.produto.Produto;
import com.sigma.ifood.ifoodMercadoApi.service.BuscaEventoPedidosService;
import com.sigma.ifood.ifoodMercadoApi.service.BuscarTokenValido;
import com.sigma.ifood.ifoodMercadoApi.service.IntegrarProdutoService;

@Service
@EnableScheduling
public class AgendamentoService {		
	private final long SEGUNDO = 1000;
	private final long CINCO_SEGUNDOS = 5000;
	private final long DEZ_SEGUNDOS = SEGUNDO * 10;
	private final long VINTE_SEGUNDOS = SEGUNDO * 20;
	private final long TRINTA_SEGUNDOS = SEGUNDO * 30;	
	private final long MINUTO = SEGUNDO * 60;
	private final long MEIA_HORA = MINUTO * 30; 
	private final long HORA = MINUTO * 60;
	
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
	
	@Scheduled(fixedDelay = CINCO_SEGUNDOS, initialDelay = MEIA_HORA)
	public void verificarEventos() {
		
		String accessToken = buscarToken.getTokenValid();
		
		List<Events> eventos = buscarEventosService.getEventos(accessToken);
		
		if(eventos != null) {		
			ObjectMapper mapper = new ObjectMapper();
			List<Eventos> domainEventos = eventos.stream()
				.map(evt -> mapper.convertValue(evt, Eventos.class))
				.collect(Collectors.toList());
			
			eventosService.salvar(domainEventos);
			domainEventos.forEach(evento -> System.out.println("Evento -> " + evento.getCodigoPedido()));
		}
		System.out.println("Serviço de verificar eventos executado");
		
	}
	
	
	@Scheduled(fixedDelay = MINUTO, initialDelay = DEZ_SEGUNDOS) public
	void integrarProdutos() {
		System.out.println("Serviço de integração de produto executado "); 
		List<ProdutoDomain> lisOfProductIntegrable = produtoDomainService.lisOfProductIntegrable();
		List<Produto> produtos = productDomainAssembler.toProdutoIfoodMercado(lisOfProductIntegrable);
		integrarProdutoService.integrarProdutos(buscarToken.getTokenValid(), produtos);
	}
	 
}
