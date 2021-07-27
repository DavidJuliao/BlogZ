package com.zup.blogspringangular.security;

import com.zup.blogspringangular.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;

@Service
public class JwtProvider {

    private Key key;

    @PostConstruct
    public void init(){
        key = Keys.secretKeyFor(SignatureAlgorithm.ES512);
    }

    public String gerarToken(Authentication authentication){
        Usuario principal = (Usuario) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsuario())
                .signWith(key)
                .compact();
    }
}
