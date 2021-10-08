package com.tsswebapps.rbasistemas.mercadoApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsswebapps.rbasistemas.mercadoApi.models.Token;
import com.tsswebapps.rbasistemas.mercadoApi.service.TokenService;

@RestController
@RequestMapping("/ifood-mercado/token")
public class TokenController {
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public void getToken(String clientId, String clientSercret) {
		Token token = tokenService.getToken(clientSercret, clientId);
		System.out.println(token.getAccess_token());
	}

}
