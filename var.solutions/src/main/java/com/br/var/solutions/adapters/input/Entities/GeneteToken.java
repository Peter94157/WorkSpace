package com.br.var.solutions.adapters.input.Entities;

import java.util.Date;

public class GeneteToken {

    private String token;
    private Date expireEM;
    private long tempoValidacao;
    private String Solicitante;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireEM() {
        return expireEM;
    }

    public void setExpireEM(Date expireEM) {
        this.expireEM = expireEM;
    }

    public long getTempoValidacao() {
        return tempoValidacao;
    }

    public void setTempoValidacao(long tempoValidacao) {
        this.tempoValidacao = tempoValidacao;
    }

    public String getSolicitante() {
        return Solicitante;
    }

    public void setSolicitante(String solicitante) {
        Solicitante = solicitante;
    }
}
