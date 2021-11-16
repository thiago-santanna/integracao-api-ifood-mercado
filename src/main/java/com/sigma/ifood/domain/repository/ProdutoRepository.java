package com.sigma.ifood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigma.ifood.domain.models.produto.ProdutoDomain;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoDomain, Long> {
	List<ProdutoDomain> findByIntegrar(Boolean integrar);
}
