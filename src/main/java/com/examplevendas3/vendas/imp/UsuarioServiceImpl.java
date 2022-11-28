package com.examplevendas3.vendas.imp;

import com.examplevendas3.vendas.domain.Usuario;
import com.examplevendas3.vendas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {


    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public UserDetails autenticar(Usuario usuario){

       UserDetails usuarioDetails = loadUserByUsername(usuario.getLogin());
       boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), usuarioDetails.getPassword() );

       if(senhasBatem){

           return usuarioDetails;

       }

       throw new RuntimeException("Semha Invalida");

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Busca o usuário do banco de dados
        Usuario usuario = usuarioRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

        // NA CLASSE USUARIO FOR IGUAL A TRUE CRIA UM ARRAY DE ADMIN E USER SE NÃO CRIA SO DE USER
        String [] roles =  usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();

    }

}
