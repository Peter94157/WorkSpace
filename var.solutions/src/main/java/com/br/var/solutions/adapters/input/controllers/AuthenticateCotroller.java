package com.br.var.solutions.adapters.input.controllers;

import com.br.var.solutions.adapters.input.Entities.GeneteToken;
import com.br.var.solutions.application.service.Entities.ValidaUsuario;
import com.br.var.solutions.infraestructure.config.security.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticateCotroller {
    @PostMapping( consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<GeneteToken> geneteToken(@RequestParam("client_id")
                                                   String client_id, @RequestParam("password") String password) {

        log.info("Iniciando a tentativa de geração de token para o usuario :"+ client_id);
        Boolean validaUsuario = ValidaUsuario.validaUsuario(client_id, password);    //Criar um enum pra validar o client_id e password

        if (Boolean.FALSE.equals(validaUsuario)) {
            log.info("não foi possivel gerar o token pois o usuario e senha estão incorretos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GeneteToken());
        }

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String token = jwtTokenUtil.generateToken(client_id);

        GeneteToken tokenResponse = new GeneteToken();
        tokenResponse.setToken(token);

        return ResponseEntity.ok(tokenResponse);
    }


}
