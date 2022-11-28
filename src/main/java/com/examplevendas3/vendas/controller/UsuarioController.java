package com.examplevendas3.vendas.controller;

import com.examplevendas3.vendas.DTO.CredenciaisDTO;
import com.examplevendas3.vendas.DTO.TokenDto;
import com.examplevendas3.vendas.domain.Usuario;
import com.examplevendas3.vendas.imp.UsuarioServiceImpl;
import com.examplevendas3.vendas.service.JwtService;
import com.examplevendas3.vendas.service.UsuarioService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private JwtService jwtService;


    private UsuarioServiceImpl usuarioServiceImp;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        passwordEncoder = null;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody @Valid Usuario usuario){

        String senha = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senha);
        usuarioService.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("/auth")
    public TokenDto autenticar(@RequestBody CredenciaisDTO credenciais){

            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getToken()).build();
            UserDetails usuarioAutenticado = usuarioServiceImp.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDto(usuario.getSenha(), token);

    }


}
