package com.sigma.ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class RbaSistemasApplication {
	@Bean
	public WebClient webClientMercado( WebClient.Builder builder) {
		return builder
				//.baseUrl("https://service.sitemercado.com.br/api/v1/")
				.baseUrl("http://localhost:3000/")
				.defaultHeader("Accept", "text/plain")
				.defaultHeader("Content-Type", "application/*+json")
				.build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RbaSistemasApplication.class, args);
	}
}