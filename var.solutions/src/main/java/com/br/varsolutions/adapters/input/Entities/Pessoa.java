package com.br.varsolutions.adapters.input.Entities;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Pessoa {

    public String nome;
    public String sobrenome;
    public String endereco;
    public int idade;
    public String time;
    public double salario;
    public double altura;
    public double peso;
    public double saldo;
    public int userId;

}
