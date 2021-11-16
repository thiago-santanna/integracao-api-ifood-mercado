package com.sigma.ifood.domain.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sigma.ifood.domain.models.produto.ProdutoDomain;
import com.sigma.ifood.ifoodMercadoApi.models.produto.Produto;

@Service
public class ProductDomainAssembler {

	public List<Produto> toProdutoIfoodMercado(List<ProdutoDomain> produtosDomain){
		ModelMapper modelMapper = new ModelMapper();
		
		List<Produto> produtos = produtosDomain.stream()
				.map(p -> modelMapper.map(p, Produto.class)).collect(Collectors.toList());
		
		return produtos;
	}
}
