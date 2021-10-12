package com.sigma.ifood.ifoodMercadoApi.controller;

import com.sigma.ifood.ifoodMercadoApi.dto.TokenDto;
import com.sigma.ifood.ifoodMercadoApi.models.Token;
import com.sigma.ifood.ifoodMercadoApi.service.IfoodMercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


public class IfoodMercadoController {
	
	@Autowired
	private IfoodMercadoService ifoodMercadoService;
	
	public Token getToken(@RequestBody TokenDto credentials) throws IOException {
		Token token = ifoodMercadoService.getToken(credentials);
		return token;

	}
	
	public void consultaEventos(){

	}

}
