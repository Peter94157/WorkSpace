package com.br.varsolutions.adapters.input.Entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PessaoResponse {
    public String nome;
    public int idade;
    public double imc;
    public String classificacaoIMC;
    public double IR;
    public double aliquota;
    public double saldoEmDolar;
    public int anoNasc;

    public String sobrenome;
    public String endereco;
    public String time;
    public String validaMundial;

    public double salario;
    public double altura;
    public double peso;

    public double saldo;


}
