package com.br.var.solutions.application.service.impl;

import com.br.var.solutions.application.service.useCase.AnoNascimentoUserCase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AnoNascimentoUserCaseImpl implements AnoNascimentoUserCase {
    public int calculoAnoNasc(int idade){
       return calculateAnoNasc(idade);
    }

    private int calculateAnoNasc(int idade) {
        LocalDate date = LocalDate.now();
        int anoAtual = date.getYear();
        return anoAtual - idade;
    }

}
