package com.sigma.ifood.ifoodMercadoApi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccessTokenDto {
	private String accessToken;
	private LocalDateTime expireIn;
}
