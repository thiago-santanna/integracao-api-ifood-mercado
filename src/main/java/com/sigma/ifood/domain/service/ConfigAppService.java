package com.sigma.ifood.domain.service;

import com.sigma.ifood.domain.models.ConfigApp;
import com.sigma.ifood.domain.repository.ConfigAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfigAppService {

    @Autowired
    private ConfigAppRepository configAppRepository;

    public ConfigApp buscar(Long id){
        Optional<ConfigApp> appOptional = configAppRepository.findById(id);

        if (appOptional.isEmpty()){
           return null;
        }

        return appOptional.get();
    }

    public boolean tokenValido(LocalDateTime dateExpireIn){
        if(dateExpireIn == null) return false;
        return dateExpireIn.isAfter(LocalDateTime.now());
    }
}
