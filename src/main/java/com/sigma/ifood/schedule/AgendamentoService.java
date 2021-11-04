package com.sigma.ifood.schedule;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.ifood.domain.models.config.ConfigApp;
import com.sigma.ifood.domain.models.pedido.Eventos;
import com.sigma.ifood.domain.service.ConfigAppService;
import com.sigma.ifood.domain.service.EventosService;
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
	
	@Autowired
	private EventosService eventosService;
	
	@Scheduled(fixedDelay = DEZ_SEGUNDOS, initialDelay = CINCO_SEGUNDOS)
	public void verificarEventos() {
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
		
		
		List<Events> eventos = buscarEventosService.getEventos(accessToken);
		
		if(eventos != null) {		
			ObjectMapper mapper = new ObjectMapper();
			List<Eventos> domainEventos = eventos.stream()
				.map(evt -> mapper.convertValue(evt, Eventos.class))
				.collect(Collectors.toList());
			
			eventosService.salvar(domainEventos);
			domainEventos.forEach(evento -> System.out.println("Evento -> " + evento.getCodigoPedido()));
		}
		System.out.println("Serviço de verificar eventos executado");
		
	}
	
	/*
	 * @Scheduled(fixedDelay = DEZ_SEGUNDOS, initialDelay = DEZ_SEGUNDOS) public
	 * void integrarProdutos() {
	 * System.out.println("Serviço de integração de produto executado "); }
	 */
}
