package com.sigma.ifood.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "config_app")
@Data
public class ConfigApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientIdIfoodMercado;
    private String clientSecretIfoodMercado;
    @Column(columnDefinition = "TEXT")
    private String token;
    private LocalDateTime expireIn;
    private Long intervaloConsulta;
}
