package com.sigma.ifood;

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

	@Override
	public void run(ApplicationArguments args) throws Exception {
		TimerTask serviceFindInformationTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Task performed on " + new Date());
			}
		};

		Timer timer = new Timer("executeService");

		long delayInit = 1000L;
		long period = 3000L;// vai ser obtido do banco de dados do app.

		timer.scheduleAtFixedRate(serviceFindInformationTask, delayInit, period);
	}
}
