package com.sigma.ifood.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.Data;

@Configuration
@EnableScheduling
@Data
public class AgendamentoService {
	private final long SEGUNDO = 1000;
	private final long DEZ_SEGUNDOS = SEGUNDO * 10;
	private final long MINUTO = SEGUNDO * 60;
	private final long MEIA_HORA = MINUTO * 30; 
	private final long HORA = MINUTO * 60;
	
	@Scheduled(fixedDelay = MINUTO, initialDelay = DEZ_SEGUNDOS)
	public void verificarEventos() {
		System.out.println("Serviço de verificar eventos executado");
	}
	
	@Scheduled(fixedDelay = MEIA_HORA, initialDelay = SEGUNDO)
	public void integrarProdutos() {
		System.out.println("Serviço de integração de produto executado");
	}
}
