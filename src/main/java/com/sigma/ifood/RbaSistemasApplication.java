package com.sigma.ifood;

import com.sigma.ifood.domain.models.ConfigApp;
import com.sigma.ifood.domain.service.ConfigAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

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

	// Aqui vou disparar o serviço de consultar dados na api do ifood Mercado
	// se novo token Vou salvar no bancode dados

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ConfigApp configApp = configAppService.buscar(1L);
		boolean tokenValido = configAppService.tokenValido(configApp.getExpireIn());
		long configPeriod = configApp.getIntervaloConsulta();

		TimerTask serviceFindInformationTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Task performed on " + new Date());
				System.out.println(tokenValido);
				System.out.println(configApp.getClientIdIfoodMercado());
			}
		};

		Timer timer = new Timer("executeService");

		long delayInit = 1000L;
		long period = configPeriod;

		timer.scheduleAtFixedRate(serviceFindInformationTask, delayInit, period);
	}
}
