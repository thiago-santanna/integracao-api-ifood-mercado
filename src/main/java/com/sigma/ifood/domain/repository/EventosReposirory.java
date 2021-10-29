package com.sigma.ifood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigma.ifood.domain.models.pedido.Eventos;

@Repository
public interface EventosReposirory extends JpaRepository<Eventos, Long> {

}
