package com.r7frank.modelag_conceit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r7frank.modelag_conceit.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
