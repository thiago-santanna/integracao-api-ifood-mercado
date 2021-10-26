package com.sigma.ifood;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.sigma.ifood.domain.service.ConfigAppService;
import com.sigma.ifood.ifoodMercadoApi.dto.PedidoVerificado;
import com.sigma.ifood.ifoodMercadoApi.models.event.Events;
import com.sigma.ifood.ifoodMercadoApi.models.pedido.Pedido;
import com.sigma.ifood.ifoodMercadoApi.models.produto.Produto;
import com.sigma.ifood.ifoodMercadoApi.service.BuscaEventoPedidosService;
import com.sigma.ifood.ifoodMercadoApi.service.BuscarPedidoService;
import com.sigma.ifood.ifoodMercadoApi.service.GerarTokenService;
import com.sigma.ifood.ifoodMercadoApi.service.IntegrarProdutoService;
import com.sigma.ifood.ifoodMercadoApi.service.VerificarEventoService;

@SpringBootApplication
public class RbaSistemasApplication {
	@Bean
	public WebClient webClientMercado( WebClient.Builder builder) {
		return builder
				//.baseUrl("https://service.sitemercado.com.br/api/v1/")
				.baseUrl("http://localhost:3030/")
				.defaultHeader("Accept", "text/plain")
				.defaultHeader("Content-Type", "application/*+json")
				.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(RbaSistemasApplication.class, args);
	}
}

@Component
class Runner implements ApplicationRunner{
	/**
	 * 	ESSA VERSÃO TRABALHARÁ COM UMA APLICACAO APENAS
	 * 	SE FOR USAR MAIS DE UMA, CRIAR UM CONTROLLER PARA RECEBER DO CLIENTE QUAL SERÁ USADA
	 */

	@Autowired
	private ConfigAppService configAppService;

	@Autowired
	private GerarTokenService gerarTokenService;
	
	@Autowired
	private BuscaEventoPedidosService buscarEventosService;
	
	@Autowired
	private BuscarPedidoService buscarPedidoService;
	
	@Autowired
	private VerificarEventoService verificarEventoService;
	
	@Autowired
	private IntegrarProdutoService integrarProdutosService;

	// Aqui vou disparar o serviço de consultar dados na api do ifood Mercado
	// se novo token Vou salvar no bancode dados
	
	// EM DESENVOLVIMENTO...

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Long period = configAppService.buscarIntervaloEvents(1L);
		Long delayInit = 1000L;//10s
		
		Long periodIntegrationProduct = configAppService.buscarIntervaloProduct(1L);
		Long delayProductInitIntegrations = 2000L;

		TimerTask serviceFindInformationTask = new TimerTask() {
			@Override
			public void run() {
				/*
				ConfigApp configApp = configAppService.buscar(1L);
				LocalDateTime expireIn = configApp.getExpireIn();
				String tokenOperation = configApp.getToken();

				if (!configAppService.tokenValido(expireIn)){
					Token token = gerarTokenService.getToken(
							new TokenDto(
									configApp.getClientIdIfoodMercado(),
									configApp.getClientSecretIfoodMercado()
							));
					tokenOperation = token.getToken_type() + " " + token.getAccess_token();
					expireIn = LocalDateTime.now().plusSeconds(Long.parseLong(token.getExpires_in()));
				}

				System.out.println("Task performed on " + new Date());
				System.out.println(configApp.getClientIdIfoodMercado());
				System.out.println(tokenOperation);
				System.out.println(expireIn);
				*/
				
				System.out.println("Pesqusa dos eventos feita");
				
				System.out.println("");
				System.out.println("Teste listando todos os eventos de pedido");
				List<Events> eventos = buscarEventosService.getEventos("TESTE");
				eventos.forEach(evento -> System.out.println("Evento -> " + evento.getCodigoPedido()));
				
				System.out.println("");
				System.out.println("Teste buscando um pedido pele código");
				
				Pedido pedido = buscarPedidoService.getPedido("TESTE", "2551-E4200485");
				System.out.println("Pedido ->  " + pedido.getCodigo() + " - Status: "+pedido.getStatus());
				
				
				System.out.println("");
				System.out.println("Teste buscando um pedido pele código");	
				
				PedidoVerificado pedidoVerificado01 = new PedidoVerificado();
				pedidoVerificado01.setId(12345678L);
				
				PedidoVerificado pedidoVerificado02 = new PedidoVerificado();
				pedidoVerificado02.setId(12345679L);				
				
				List<PedidoVerificado> pedidosVerificados = new ArrayList<>();
				pedidosVerificados.add(pedidoVerificado01);
				pedidosVerificados.add(pedidoVerificado02);
				
				verificarEventoService.verificaPedido(pedidosVerificados, "TESTE");
				
				
				System.out.println("");
				System.out.println("Teste Enviando produtos.");	
				
				Produto prod01 = new Produto();
				Produto prod02 = new Produto();
				Produto prod03 = new Produto();
				Produto prod04 = new Produto();
				Produto prod05 = new Produto();
				
				prod01.setNome("PRODUTO 01");
				prod02.setNome("PRODUTO 02");
				prod03.setNome("PRODUTO 03");
				prod04.setNome("PRODUTO 04");
				prod05.setNome("PRODUTO 05");
				
				prod01.setValor(BigDecimal.valueOf(10));
				prod02.setValor(BigDecimal.valueOf(20));
				prod03.setValor(BigDecimal.valueOf(30));
				prod04.setValor(BigDecimal.valueOf(40));
				prod05.setValor(BigDecimal.valueOf(50));
				
				List<Produto> produtos = new ArrayList<>();
				produtos.add(prod01);
				produtos.add(prod02);
				produtos.add(prod03);
				produtos.add(prod04);
				produtos.add(prod05);
				
				integrarProdutosService.integrarProdutos("TESTE", produtos);
				
			}
		};
		
		/*
		TimerTask serviceIntegrationProduct = new TimerTask() {

			@Override
			public void run() {
				System.out.println("integração de produto feita");				
			}
			
		};*/

		Timer timerEvents = new Timer("executeServiceEvents");
		timerEvents.scheduleAtFixedRate(serviceFindInformationTask, delayInit, period);
		
		//Timer timerProduct = new Timer("executeServiceProdutos");
		//timerProduct.scheduleAtFixedRate(serviceIntegrationProduct, delayProductInitIntegrations, periodIntegrationProduct);
	}
}
