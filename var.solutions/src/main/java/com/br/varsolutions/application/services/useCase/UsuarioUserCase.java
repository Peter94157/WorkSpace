package com.br.varsolutions.application.services.useCase;

import com.br.varsolutions.adapters.input.Entities.Usuario;


public interface UsuarioUserCase {

    Usuario cadastraUsuario(Usuario user);

    Usuario bucasUsuario (Usuario user);

    Usuario AtualizarUsuario(Usuario user, Long id);

}
