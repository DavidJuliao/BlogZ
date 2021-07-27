package com.zup.blogspringangular.controller;

import com.zup.blogspringangular.model.dto.UsuarioDto;
import com.zup.blogspringangular.model.form.UsuarioForm;
import com.zup.blogspringangular.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signup(@RequestBody UsuarioForm registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDto usuarioDto){
        authService.login(usuarioDto);
        return authService.login(usuarioDto);
    }
}
