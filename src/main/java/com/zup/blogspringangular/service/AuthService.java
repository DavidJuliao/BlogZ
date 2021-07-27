package com.zup.blogspringangular.service;

import com.zup.blogspringangular.model.Usuario;
import com.zup.blogspringangular.model.dto.UsuarioDto;
import com.zup.blogspringangular.model.form.UsuarioForm;
import com.zup.blogspringangular.repository.UsuarioRepository;
import com.zup.blogspringangular.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    public void signup(UsuarioForm registerRequest) {
        Usuario usuario = new Usuario(registerRequest.getUsuario(), codificarSenha(registerRequest.getSenha()), registerRequest.getEmail());
        usuarioRepository.save(usuario);

    }

    private String codificarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public String login(UsuarioDto usuarioDto) {
        Authentication authentication = authenticationManager
                                                    .authenticate(
                                                            new UsernamePasswordAuthenticationToken(
                                                                    usuarioDto.getUsuario(),
                                                                    usuarioDto.getSenha()
                                                            )
                                                    );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.gerarToken(authentication);
    }
}
