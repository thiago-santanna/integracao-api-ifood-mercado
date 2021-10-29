package com.sigma.ifood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigma.ifood.domain.models.config.ConfigApp;
import com.sigma.ifood.domain.repository.ConfigAppRepository;

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
    
    @Transactional
    public ConfigApp salvar(ConfigApp configApp) {
    	return configAppRepository.save(configApp);
    }

	/*
	 * public final Long buscarIntervaloEvents(Long idApp){ final Long interval =
	 * configAppRepository.findIntervaloConsultaPedido(idApp); return interval; }
	 * 
	 * public Long buscarIntervaloProduct(Long idApp){ Long interval =
	 * configAppRepository.findIntervaloIntegrarProduto(idApp); return interval; }
	 */
}
