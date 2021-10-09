package com.tsswebapps.rbasistemas.ifoodMercadoApi.models;

import lombok.Data;

@Data
public class Token {
	private String access_token;
	private String token_type;
	private String expires_in;
}
