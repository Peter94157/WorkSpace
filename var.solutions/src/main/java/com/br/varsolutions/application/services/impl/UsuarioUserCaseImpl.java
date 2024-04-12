package com.br.varsolutions.application.services.impl;

import com.br.varsolutions.adapters.input.Entities.Usuario;
import com.br.varsolutions.adapters.input.controllers.UsuarioController;
import com.br.varsolutions.application.services.useCase.UsuarioUserCase;
import com.br.varsolutions.domain.entities.UsuarioEntity;
import com.br.varsolutions.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioUserCaseImpl implements UsuarioUserCase {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario bucasUsuario(Usuario user) {
        return getUser(user);
    }

    private Usuario getUser(Usuario user) {

        UsuarioEntity novoUsuario = usuarioRepository.findByUsuario(user.getUsuario());

        if (Objects.nonNull(novoUsuario) && novoUsuario.getSenha().equals(user.getSenha())) {
            return Usuario.builder().id(novoUsuario.getId())
                    .nome(novoUsuario.getNome())
                    .senha(novoUsuario.getSenha())
                    .build();
        }
        return null;
    }

    public Usuario cadastraUsuario(Usuario user) {
        return cadastroUser(user);
    }

    private Usuario cadastroUser(Usuario user) {

        UsuarioEntity entity = new UsuarioEntity();

        entity.setUsuario(user.getUsuario());
        entity.setNome(user.getNome());
        entity.setSenha(user.getSenha());

        UsuarioEntity novoUsuario = usuarioRepository.save(entity);

        return Usuario.builder()
                .id(novoUsuario.getId())
                .nome(novoUsuario.getNome())
                .usuario(novoUsuario.getUsuario())
                .senha(novoUsuario.getSenha())
                .build();
    }
    public Usuario AtualizarUsuario(Usuario user, Long id){
        return atualizaUser(user, id);
    }

    private Usuario atualizaUser(Usuario user, Long id) {
        Integer intId = (int) (long) id;
        Optional<UsuarioEntity> UsuarioExistente = Optional.ofNullable(usuarioRepository.findById(intId));

        if(UsuarioExistente.isPresent()){
            UsuarioEntity retornoExistente = UsuarioExistente.get();
            retornoExistente.setSenha(user.getSenha());
            UsuarioEntity UsuarioAtualizado = usuarioRepository.save(retornoExistente);

            return Usuario.builder()
                    .nome(UsuarioAtualizado.getNome())
                    .usuario(UsuarioAtualizado.getUsuario())
                    .build();
        }
        return null;
    }
}
