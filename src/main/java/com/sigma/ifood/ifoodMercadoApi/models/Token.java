package com.sigma.ifood.ifoodMercadoApi.models;

import lombok.Data;

@Data
public class Token {
	private String access_token;
	private String token_type;
	private String expires_in;

	public String accessTokenValue(){
		return this.token_type + " " + this.access_token;
	}
}
