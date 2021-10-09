package com.tsswebapps.rbasistemas.mercadoApi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsswebapps.rbasistemas.mercadoApi.dto.TokenDto;
import com.tsswebapps.rbasistemas.mercadoApi.models.Token;
import com.tsswebapps.rbasistemas.mercadoApi.service.TokenService;

@RestController
@RequestMapping("/ifood-mercado/token")
public class TokenController {
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<Token> getToken(@RequestBody TokenDto credenciais) throws IOException {
		Token token = tokenService.getToken(credenciais);
		return ResponseEntity.ok(token);

	}

}
