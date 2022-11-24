package com.examplevendas3.vendas.validation;

import com.examplevendas3.vendas.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("30")
    private String expiracao;
    @Value("d2VzbGV5")
    private String chaveAssinatura;


    public String gerarToken(Usuario usuario){

        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Date data = Date.from(Instant.from(dataHoraExpiracao.atZone(ZoneId.systemDefault())));

        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    private Claims obterClims(String toker) throws ExpiredJwtException {

    return Jwts
            .parser()
            .setSigningKey(chaveAssinatura)
            .parseClaimsJws(toker)
            .getBody();

    }

    public boolean tokenValido( String token){

        try {

            Claims claims = obterClims(token);
            Date dataexpiracao = claims.getExpiration();
            LocalDateTime data = dataexpiracao
                    .toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();

            return  LocalDateTime.now().isAfter(data);

        }catch ( Exception e){

            return false;

        }

    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException{


        return (String) obterClims(token).getSubject();

    }
}
