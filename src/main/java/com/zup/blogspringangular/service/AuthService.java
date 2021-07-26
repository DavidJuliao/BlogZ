package com.zup.blogspringangular.service;

import com.zup.blogspringangular.model.Usuario;
import com.zup.blogspringangular.model.dto.RegisterRequest;
import com.zup.blogspringangular.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void signup(RegisterRequest registerRequest) {
        Usuario usuario = new Usuario(registerRequest.getUsuario(), registerRequest.getSenha(), registerRequest.getEmail());
        usuarioRepository.save(usuario);

    }
}
