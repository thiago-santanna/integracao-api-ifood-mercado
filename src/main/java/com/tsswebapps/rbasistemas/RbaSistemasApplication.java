package com.tsswebapps.rbasistemas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class RbaSistemasApplication {
	
	//Um Bean para cada API que tenha que consumir
	@Bean
	public WebClient webClientMercado( WebClient.Builder builder) {
		return builder
				.baseUrl("https://service.sitemercado.com.br/api/v1")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}	

	public static void main(String[] args) {
		SpringApplication.run(RbaSistemasApplication.class, args);
	}

}
