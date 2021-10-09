package com.tsswebapps.rbasistemas.mercadoApi.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TokenDto {
	
	@NotBlank
	private String client_id;
	
	@NotBlank
	private String client_secret;
}
