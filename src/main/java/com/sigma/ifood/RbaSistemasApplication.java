package com.sigma.ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootApplication 
public class RbaSistemasApplication {
	@Bean
	public WebClient webClientMercado( WebClient.Builder builder) {
		return builder
				//.baseUrl("http://localhost:3000/")
				.baseUrl("https://service.sitemercado.com.br/api/v1/")
				.defaultHeader("Accept", "text/plain")
				.defaultHeader("Content-Type", "application/*+json")
				.filter(logRequest())
				.build();
	}
	
	private ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			log.info("Request {} {}", clientRequest.method(), clientRequest.url());
			return Mono.just(clientRequest);
		});
	}	
	
	public static void main(String[] args) {
		SpringApplication.run(RbaSistemasApplication.class, args);
	}
}