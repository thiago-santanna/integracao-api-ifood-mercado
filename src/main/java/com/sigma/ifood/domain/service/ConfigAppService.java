package com.sigma.ifood.domain.service;

import com.sigma.ifood.domain.models.config.ConfigApp;
import com.sigma.ifood.domain.repository.ConfigAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfigAppService {

    @Autowired
    private ConfigAppRepository configAppRepository;

    public ConfigApp buscar(Long idApp){
        Optional<ConfigApp> appOptional = configAppRepository.findById(idApp);

        if (appOptional.isEmpty()){
           return null;
        }

        return appOptional.get();
    }

    public Long buscarIntervaloEvents(Long idApp){
        Long interval = configAppRepository.findIntervaloConsultaPedido(idApp);
        return interval;
    }
    
    public Long buscarIntervaloProduct(Long idApp){
        Long interval = configAppRepository.findIntervaloIntegrarProduto(idApp);
        return interval;
    }

    public boolean tokenValido(LocalDateTime dateExpireIn){
        if(dateExpireIn == null) return false;
        return dateExpireIn.isBefore(LocalDateTime.now());
    }
}
