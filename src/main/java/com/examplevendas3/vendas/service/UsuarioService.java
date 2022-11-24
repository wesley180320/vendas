package com.examplevendas3.vendas.service;

import com.examplevendas3.vendas.domain.Usuario;
import com.examplevendas3.vendas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    public Usuario save(Usuario usuario){

     return  usuarioRepository.save(usuario);

    }

}
