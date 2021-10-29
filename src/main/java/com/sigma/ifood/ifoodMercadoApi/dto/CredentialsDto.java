package com.sigma.ifood.ifoodMercadoApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialsDto {
    private String client_id;
    private String client_secret;
}
