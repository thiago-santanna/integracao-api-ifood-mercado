package com.tsswebapps.rbasistemas.ifoodMercadoApi.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TokenDto {
	
	@NotBlank
	private String client_id;
	
	@NotBlank
	private String client_secret;
}
