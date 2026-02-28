package com.hugojanuario.deploy_manager.controller;

import com.hugojanuario.deploy_manager.domain.user.User;
import com.hugojanuario.deploy_manager.domain.user.dto.UserCreateRequest;
import com.hugojanuario.deploy_manager.infra.security.DataTokenJWT;
import com.hugojanuario.deploy_manager.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserCreateRequest userCreateRequest){
        var tokenAuth = new UsernamePasswordAuthenticationToken(userCreateRequest.email(), userCreateRequest.password());
        var auth = manager.authenticate(tokenAuth);

        var tokenJWT = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }

}