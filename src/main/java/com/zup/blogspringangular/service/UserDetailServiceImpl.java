package com.zup.blogspringangular.service;

import com.zup.blogspringangular.model.Usuario;
import com.zup.blogspringangular.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario usuarioLogado = usuarioRepository.findByUsuario(usuario)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario invalido"));
        return new User(usuarioLogado.getUsuario(),
                usuarioLogado.getSenha(),
                true,true,true, true,
                getAutorizacao()
        );
    }

    private Collection<? extends GrantedAuthority> getAutorizacao(){
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }
}
