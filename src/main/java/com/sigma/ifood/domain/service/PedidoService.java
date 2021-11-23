package com.sigma.ifood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigma.ifood.domain.models.pedido.PedidoDomain;
import com.sigma.ifood.domain.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public PedidoDomain salvar(PedidoDomain pedidoDomain) {
		return pedidoRepository.save(pedidoDomain);
	}
}
