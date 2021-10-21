package com.sigma.ifood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigma.ifood.domain.models.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	List<Produto> findByIntegrar(Boolean integrar);
}
