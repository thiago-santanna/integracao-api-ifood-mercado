package com.sigma.ifood.domain.models.config;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity()
@Table(name = "config_app")
public class ConfigApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientIdIfoodMercado;
    private String clientSecretIfoodMercado;
    @Column(columnDefinition = "TEXT")
    private String token;
    private LocalDateTime expireIn;
    private Long intervaloConsultaPedido;
    private Long intervaloIntegrarProduto;

    public ConfigApp(){}
}
