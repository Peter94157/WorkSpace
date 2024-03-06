package com.br.var.solutions.adapters.input.Entities;

public class PessaoResponse {
    public String nome;
    public int idade;
    public String imc;
    public String classificacaoIMC;
    public String IR;
    public String aliquota;
    public String saldoEmDolar;
    public int anoNasc;

    public String sobrenome;
    public String endereco;
    public String time;
    public String validaMundial;

    public String salario;
    public double altura;
    public double peso;

    public double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getImc() {
        return imc;
    }

    public String getValidaMundial() {
        return validaMundial;
    }

    public void setValidaMundial(String validaMundial) {
        this.validaMundial = validaMundial;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getClassificacaoIMC() {
        return classificacaoIMC;
    }

    public void setClassificacaoIMC(String classificacaoIMC) {
        this.classificacaoIMC = classificacaoIMC;
    }

    public String getIR() {
        return IR;
    }

    public void setIR(String IR) {
        this.IR = IR;
    }

    public String getAliquota() {
        return aliquota;
    }

    public void setAliquota(String aliquota) {
        this.aliquota = aliquota;
    }

    public String getSaldoEmDolar() {
        return saldoEmDolar;
    }

    public void setSaldoEmDolar(String saldoEmDolar) {
        this.saldoEmDolar = saldoEmDolar;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getSalario() {
        return salario;
    }
    public void setSalario(String salario) {
        this.salario = salario;
    }
    public double getAltura() {
        return altura;
    }
    public void setAltura(double altura) {
        this.altura = altura;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }

}
