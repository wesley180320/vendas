package com.examplevendas3.vendas.security.jwt;

import com.examplevendas3.vendas.imp.UsuarioServiceImpl;
import com.examplevendas3.vendas.service.JwtService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter  extends OncePerRequestFilter {

    private JwtService jwtService;
    private UsuarioServiceImpl usuarioService;


    public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl usuarioService) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


          String authorization = request.getHeader("Authorization");


          if(authorization != null && authorization.startsWith("Bearer")){


              String token = authorization.split("")[1];

              Boolean isValid = jwtService.tokenValido(token);

              if(isValid){

                  String loginUsuario= jwtService.obterLoginUsuario(token);
                  UserDetails usuario = usuarioService.loadUserByUsername(loginUsuario);

                  UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                  user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                  SecurityContextHolder.getContext().setAuthentication(user);

              }

          }

          filterChain.doFilter(request, response);
    }


}
