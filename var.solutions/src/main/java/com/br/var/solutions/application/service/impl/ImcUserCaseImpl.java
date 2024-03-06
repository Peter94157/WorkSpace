package com.br.var.solutions.application.service.impl;

import com.br.var.solutions.application.service.Entities.InfoIMC;
import com.br.var.solutions.application.service.useCase.ImcUseCase;
import org.springframework.stereotype.Service;

@Service
public class ImcUserCaseImpl implements ImcUseCase {

    public InfoIMC calculoIMC(double peso, double altura){
        return calculateImc(peso, altura);
    }



    private InfoIMC calculateImc(double peso, double altura) {
        double imc = peso / (altura * altura);

        if (imc < 18.5) {
            return InfoIMC.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("Abaixo do peso")
                    .build();
        } else if (imc >= 18.5 && imc <= 24.9) {
            return InfoIMC.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("Peso normal")
                    .build();
        } else if (imc > 24.9 && imc <= 29.9) {
            return InfoIMC.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("Excesso de peso")
                    .build();
        } else if (imc > 29.9 && imc <= 34.9) {
            return InfoIMC.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("Obessidade I")
                    .build();
        } else if (imc > 34.9 && imc <= 39.9) {
            return InfoIMC.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("Obessidade II")
                    .build();
        } else {
            return InfoIMC.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("Obessidade III")
                    .build();
        }
    }
}
