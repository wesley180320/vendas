package com.examplevendas3.vendas.repository;

import com.examplevendas3.vendas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

}
