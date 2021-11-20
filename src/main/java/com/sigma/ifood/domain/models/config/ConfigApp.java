package com.sigma.ifood.domain.models.config;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

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

    public ConfigApp(){}
}
