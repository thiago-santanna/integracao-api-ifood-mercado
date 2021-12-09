package com.sigma.ifood.ifoodMercadoApi.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sigma.ifood.ifoodMercadoApi.dto.CredentialsDto;
import com.sigma.ifood.exceptions.ApiException;
import com.sigma.ifood.ifoodMercadoApi.dto.AccessTokenDto;
import com.sigma.ifood.ifoodMercadoApi.models.token.Token;

import reactor.core.publisher.Mono;

@Service
public class GerarTokenService {
	
	@Autowired
	private WebClient webClientMercado;
	
	private Token getToken(CredentialsDto credenciais) throws IOException {
		try {
			Mono<Token> monoToken = this.webClientMercado
					.method(HttpMethod.POST)
					.uri("oauth/token")
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just(credenciais), CredentialsDto.class) 
					.retrieve()
					.onStatus(
							HttpStatus::is5xxServerError, clinetResponse -> clinetResponse.bodyToMono(String.class).map(
							body -> new ApiException("Codigo: "+clinetResponse.statusCode().toString() + "\nDetalhes"+ body))
					)			
					.onStatus(
							HttpStatus::is4xxClientError, clinetResponse -> clinetResponse.bodyToMono(String.class).map(
							body -> new ApiException("Codigo: "+clinetResponse.statusCode().toString() + "\nDetalhes"+ body))
					)					
					.bodyToMono(Token.class);
	
			return monoToken.block();			
		} catch (RuntimeException e) {
			throw new ApiException(e.getMessage(), "getToken", credenciais);
		}
	}
	
    private boolean tokenValido(LocalDateTime dateExpireIn){    	
        if(dateExpireIn == null) return false;        
        return !dateExpireIn.isBefore(LocalDateTime.now());
    }
    
    public AccessTokenDto gerarOuValidarToken(LocalDateTime expireIn, String clientId, String clientSecret) throws IOException {
    	
		if (!tokenValido(expireIn)){
			Token token = this.getToken(
					new CredentialsDto(
							clientId,
							clientSecret
					));
			//System.out.println("gerou novo token");
			return new AccessTokenDto(
					token.getToken_type() + " " + token.getAccess_token(),
					LocalDateTime.now().plusSeconds(Long.parseLong(token.getExpires_in()))
					);
		}
		
		return null;
    }
}
