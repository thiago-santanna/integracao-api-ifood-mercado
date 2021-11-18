package com.sigma.ifood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigma.ifood.domain.models.pedido.Eventos;
import com.sigma.ifood.domain.repository.EventosReposirory;

@Service
public class EventosService {
	@Autowired
	private EventosReposirory eventosReposirory;
	
	@Transactional
	public List<Eventos> salvar(List<Eventos> eventos) {
		return eventosReposirory.saveAll(eventos);
	}
}
