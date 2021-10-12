package com.sigma.ifood.domain.repository;

import com.sigma.ifood.domain.models.ConfigApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigAppRepository extends JpaRepository<ConfigApp, Long> {
    @Query("SELECT c.intervaloConsulta FROM ConfigApp c WHERE c.id = :id")
    Long findIntervaloConsulta(@Param("id") Long appId);
}