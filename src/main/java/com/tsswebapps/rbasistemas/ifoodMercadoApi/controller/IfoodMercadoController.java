package com.tsswebapps.rbasistemas.ifoodMercadoApi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsswebapps.rbasistemas.ifoodMercadoApi.dto.TokenDto;
import com.tsswebapps.rbasistemas.ifoodMercadoApi.models.Token;
import com.tsswebapps.rbasistemas.ifoodMercadoApi.service.IfoodMercadoService;

@RestController
@RequestMapping("/ifood-mercado")
public class IfoodMercadoController {
	
	@Autowired
	private IfoodMercadoService ifoodMercadoService;
	
	@PostMapping("/token")
	public ResponseEntity<Token> getToken(@RequestBody TokenDto credenciais) throws IOException {
		Token token = ifoodMercadoService.getToken(credenciais);
		return ResponseEntity.ok(token);

	}
	
	public void consultaEventos(){
		
	}

}
