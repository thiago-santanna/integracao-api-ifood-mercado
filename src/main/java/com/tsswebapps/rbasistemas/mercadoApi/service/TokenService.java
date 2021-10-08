package com.tsswebapps.rbasistemas.mercadoApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.tsswebapps.rbasistemas.mercadoApi.models.Token;

@Service
public class TokenService {
	
	@Autowired
	private WebClient webClientMercado;
	
	public Token getToken(String client_id, String client_secret) {
		MultiValueMap<String, String> credentials = new LinkedMultiValueMap<>();
		
		credentials.add("client_id", client_id);
		credentials.add("client_secret", client_secret);
		
		Token token = this.webClientMercado
				.method(HttpMethod.POST)
				.uri("oauth/token")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromFormData(credentials))
				.retrieve()
				.bodyToMono(Token.class)
				.block();
		
		System.out.println(token);
		
		return token;
	}

}
