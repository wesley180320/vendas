package com.examplevendas3.vendas.repository;

import com.examplevendas3.vendas.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {


    @Query(value = " select * from cliente c where c.name like '%:name%' ", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("name") String name);

//    @Query(value = "delete  from cliente c where c.name =:name")
//    @Modifying
//    Cliente deleletarPorNome(@Param("name") String nome);

}
