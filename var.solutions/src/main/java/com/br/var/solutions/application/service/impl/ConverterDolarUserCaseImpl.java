package com.br.var.solutions.application.service.impl;

import com.br.var.solutions.application.service.useCase.ConverterDolarUserCase;
import org.springframework.stereotype.Service;

@Service
public class ConverterDolarUserCaseImpl implements ConverterDolarUserCase {


    public String converterDolar(double saldo){
        return conversaoDolar(saldo);
    }
    private String conversaoDolar(double saldo) {
        return String.valueOf(saldo / 5.11);
    }

}
