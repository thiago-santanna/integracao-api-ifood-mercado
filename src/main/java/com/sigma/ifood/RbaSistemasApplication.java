package com.sigma.ifood;

import java.time.LocalDateTime;
import java.util.Date;
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

import com.sigma.ifood.domain.models.config.ConfigApp;
import com.sigma.ifood.domain.service.ConfigAppService;
import com.sigma.ifood.ifoodMercadoApi.dto.TokenDto;
import com.sigma.ifood.ifoodMercadoApi.models.token.Token;
import com.sigma.ifood.ifoodMercadoApi.service.GerarTokenService;

@SpringBootApplication
public class RbaSistemasApplication {
	@Bean
	public WebClient webClientMercado( WebClient.Builder builder) {
		return builder
				.baseUrl("https://service.sitemercado.com.br/api/v1/")
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
				///*
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
				//*/
				
				System.out.println("Pesqusa dos eventos feita");
			}
		};
		
		TimerTask serviceIntegrationProduct = new TimerTask() {

			@Override
			public void run() {
				System.out.println("integração de produto feita");				
			}
			
		};

		Timer timerEvents = new Timer("executeServiceEvents");
		timerEvents.scheduleAtFixedRate(serviceFindInformationTask, delayInit, period);
		
		Timer timerProduct = new Timer("executeServiceProdutos");
		timerProduct.scheduleAtFixedRate(serviceIntegrationProduct, delayProductInitIntegrations, periodIntegrationProduct);
	}
}
