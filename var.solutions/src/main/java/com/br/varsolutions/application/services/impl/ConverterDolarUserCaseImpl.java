package com.br.varsolutions.application.services.impl;

import com.br.varsolutions.application.services.useCase.ConverterDolarUserCase;
import org.springframework.stereotype.Service;

@Service
public class ConverterDolarUserCaseImpl implements ConverterDolarUserCase {


    public double converterDolar(double saldo){
        return conversaoDolar(saldo);
    }
    private double conversaoDolar(double saldo) {
        return saldo / 5.11;
    }

}
