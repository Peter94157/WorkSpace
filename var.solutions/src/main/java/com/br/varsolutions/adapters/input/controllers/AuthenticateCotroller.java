package com.br.varsolutions.adapters.input.controllers;

import com.br.varsolutions.adapters.input.Entities.GeneteToken;
import com.br.varsolutions.adapters.input.Entities.Usuario;
import com.br.varsolutions.adapters.output.useCase.RedisUseCase;
import com.br.varsolutions.application.services.useCase.UsuarioUserCase;
import com.br.varsolutions.infraestructure.config.security.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticateCotroller {

    @Autowired
    UsuarioUserCase usuarioUserCase;

    @Autowired
    RedisUseCase redisUseCase;

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<GeneteToken> geneteToken(@RequestParam("client_id")
                                                   String client_id, @RequestParam("password") String password) {





        log.info("Iniciando a tentativa de geração de token para o usuario :" + client_id);

        int hashKey = client_id.hashCode()+password.hashCode();
        log.info("Inciando consulta no Redis para o Usuario "+client_id);
        String tokenRedis = redisUseCase.recuperarDadosRedis(hashKey);

        if (tokenRedis != null){

            log.info("Dados recuperados do Redis, reaproveitando token.");
            GeneteToken tokenDoRedis = GeneteToken.builder()
                    .token(tokenRedis)
                    .usuarioId(0)
                    .build();
            log.info("token enviado");
            return ResponseEntity.ok(tokenDoRedis);

        }
        log.info("Token nao encontrado no Redis, gerando um novo token");
        Usuario user = usuarioUserCase.bucasUsuario(Usuario.builder()
                .usuario(client_id)
                .senha(password)
                .build()); //Criar um enum pra validar o client_id e password

        if (Objects.isNull(user)) {
            log.info("não foi possivel gerar o token pois o usuario e senha estão incorretos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GeneteToken.builder().build());
        }



        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String token = jwtTokenUtil.generateToken(client_id);

        GeneteToken tokenResponse = GeneteToken.builder()
                .token(token)
                .usuarioId(user.getId())
                .build();

        log.info("Token gerado com sucesso para o usuaro: "+client_id+" em: "+System.currentTimeMillis());


        redisUseCase.salvarDadosNoRedis(hashKey,token,120);

        log.info("token enviado");
        return ResponseEntity.ok(tokenResponse);
    }


}
