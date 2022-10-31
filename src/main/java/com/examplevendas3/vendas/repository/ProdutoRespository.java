package com.examplevendas3.vendas.repository;

import com.examplevendas3.vendas.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRespository extends JpaRepository< Produto, Integer> {
}
