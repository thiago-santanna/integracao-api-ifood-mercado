package com.sigma.ifood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigma.ifood.domain.models.produto.ProdutoDomain;
import com.sigma.ifood.domain.repository.ProdutoRepository;


@Service
public class ProdutoDomainService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<ProdutoDomain> lisOfProductIntegrable(){
		List<ProdutoDomain> productsDomain = produtoRepository.findByIntegrar(true);
		return productsDomain;
		
	}
	
	@Transactional
	public void updatedProductsIntegrated(List<ProdutoDomain> products){
		produtoRepository.saveAll(products);
	}
}
