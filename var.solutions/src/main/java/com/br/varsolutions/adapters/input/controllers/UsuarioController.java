package com.br.varsolutions.adapters.input.controllers;

import com.br.varsolutions.adapters.input.Entities.Usuario;
import com.br.varsolutions.application.services.useCase.UsuarioUserCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
@Slf4j
public class UsuarioController {

    @Autowired
    UsuarioUserCase usuarioUserCase;

    @PostMapping
    public ResponseEntity<Usuario> post(@RequestBody Usuario user){

    log.info("iniciando um novo cadastro de usuario! ");
    Usuario novoUsuarioCadastrado = usuarioUserCase.cadastraUsuario(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuarioCadastrado);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> put (@RequestBody Usuario user, @PathVariable Long id){
        log.info("Inciando a atualização de um usuario existente. "+id);
        Usuario userAtualizado = usuarioUserCase.AtualizarUsuario(user, id);

        if (Objects.isNull(userAtualizado)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userAtualizado);
        }
          return ResponseEntity.status(HttpStatus.OK).body(userAtualizado);
        }


}
