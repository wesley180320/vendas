package com.examplevendas3.vendas.controller;

import com.examplevendas3.vendas.domain.Usuario;
import com.examplevendas3.vendas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UsuarioController() {
        passwordEncoder = null;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody @Valid Usuario usuario){

        String senha = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senha);
        usuarioService.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
