package com.sigma.ifood.domain.repository;

import com.sigma.ifood.domain.models.ConfigApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigAppRepository extends JpaRepository<ConfigApp, Long> {
}
