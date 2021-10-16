package com.sigma.ifood.domain.repository;

import com.sigma.ifood.domain.models.ConfigApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigAppRepository extends JpaRepository<ConfigApp, Long> {
    @Query("SELECT c.intervaloConsultaPedido FROM ConfigApp c WHERE c.id = :id")
    Long findIntervaloConsultaPedido(@Param("id") Long appId);
    
    @Query("SELECT c.intervaloIntegrarProduto FROM ConfigApp c WHERE c.id = :id")
    Long findIntervaloIntegrarProduto(@Param("id") Long appId);
}
