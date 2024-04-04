package com.br.varsolutions.adapters.input.Entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class GeneteToken {

   private String token;
   private int usuarioId;
   private Date expiraem;
   private long tempoValidacao;
   private String solicitant;
}
