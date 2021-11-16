package com.sigma.ifood.ifoodMercadoApi.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigma.ifood.domain.models.config.ConfigApp;
import com.sigma.ifood.domain.service.ConfigAppService;
import com.sigma.ifood.ifoodMercadoApi.dto.AccessTokenDto;

@Service
public class BuscarTokenValido {
	
	@Autowired
	private ConfigAppService configAppService;

	@Autowired
	private GerarTokenService gerarTokenService;
	
	
	public String getTokenValid() {
		//Se um dia for precisar usar mais de uma credencial, fazer um controller e o client deve informar qual app quer buscar
		ConfigApp configApp = configAppService.buscar(1L);
		
		//Access Token atual se após testado ainda for válido será usado, caso contrário será atualizado e salvo no banco
		LocalDateTime expireIn = configApp.getExpireIn();
		String accessToken = configApp.getToken();
		
		String clientId = configApp.getClientIdIfoodMercado();
		String clientSecret = configApp.getClientSecretIfoodMercado(); 
		
		AccessTokenDto accessTokenDto = gerarTokenService.gerarOuValidarToken(expireIn, clientId, clientSecret);
		
		if(accessTokenDto != null) {
			expireIn = accessTokenDto.getExpireIn();
			accessToken = accessTokenDto.getAccessToken();
			System.out.println("Salvou cofigurações");
			configApp.setExpireIn(expireIn);
			configApp.setToken(accessToken);
			configAppService.salvar(configApp);			
		}
		
		return accessToken;
	};
}
