package com.sigma.ifood;

import com.sigma.ifood.domain.models.ConfigApp;
import com.sigma.ifood.domain.service.ConfigAppService;
import com.sigma.ifood.ifoodMercadoApi.dto.TokenDto;
import com.sigma.ifood.ifoodMercadoApi.models.Token;
import com.sigma.ifood.ifoodMercadoApi.service.IfoodMercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
	private IfoodMercadoService ifoodMercadoService;

	// Aqui vou disparar o serviço de consultar dados na api do ifood Mercado
	// se novo token Vou salvar no bancode dados

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Long period = configAppService.buscarIntervalo(1L);
		Long delayInit = 1000L;

		TimerTask serviceFindInformationTask = new TimerTask() {
			@Override
			public void run() {
				ConfigApp configApp = configAppService.buscar(1L);
				LocalDateTime expireIn = configApp.getExpireIn();
				String tokenOperation = configApp.getToken();

				if (!configAppService.tokenValido(expireIn)){
					Token token = ifoodMercadoService.getToken(
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
			}
		};

		Timer timer = new Timer("executeService");
		timer.scheduleAtFixedRate(serviceFindInformationTask, delayInit, period);
	}
}
