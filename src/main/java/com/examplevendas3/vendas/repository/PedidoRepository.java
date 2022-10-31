package com.examplevendas3.vendas.repository;

import com.examplevendas3.vendas.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository< Pedido,Integer> {
}
