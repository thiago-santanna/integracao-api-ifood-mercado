package com.sigma.ifood.schedule;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sigma.ifood.domain.models.config.ConfigApp;
import com.sigma.ifood.domain.service.ConfigAppService;
import com.sigma.ifood.ifoodMercadoApi.dto.AccessTokenDto;
import com.sigma.ifood.ifoodMercadoApi.models.event.Events;
import com.sigma.ifood.ifoodMercadoApi.service.BuscaEventoPedidosService;
import com.sigma.ifood.ifoodMercadoApi.service.GerarTokenService;

@Service
@EnableScheduling
public class AgendamentoService {		
	private final long SEGUNDO = 1000;
	private final long CINCO_SEGUNDOS = 5000;
	private final long DEZ_SEGUNDOS = SEGUNDO * 10;
	private final long VINTE_SEGUNDOS = SEGUNDO * 20;
	private final long TRINTA_SEGUNDOS = SEGUNDO * 30;	
	private final long MINUTO = SEGUNDO * 60;
	private final long MEIA_HORA = MINUTO * 30; 
	private final long HORA = MINUTO * 60;
	
	@Autowired
	private ConfigAppService configAppService;

	@Autowired
	private GerarTokenService gerarTokenService;
	
	@Autowired
	private BuscaEventoPedidosService buscarEventosService;
	
	@Scheduled(fixedDelay = DEZ_SEGUNDOS, initialDelay = CINCO_SEGUNDOS)
	public void verificarEventos() {
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
			
			configApp.setExpireIn(expireIn);
			configApp.setToken(accessToken);
			configAppService.salvar(configApp);			
		}
		
		
		List<Events> eventos = buscarEventosService.getEventos(accessToken);
		eventos.forEach(evento -> System.out.println("Evento -> " + evento.getCodigoPedido()));		
		System.out.println("Serviço de verificar eventos executado");
		
	}
	
	/*
	 * @Scheduled(fixedDelay = DEZ_SEGUNDOS, initialDelay = DEZ_SEGUNDOS) public
	 * void integrarProdutos() {
	 * System.out.println("Serviço de integração de produto executado "); }
	 */
}
