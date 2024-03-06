package com.br.var.solutions.application.service.impl;

import com.br.var.solutions.application.service.Entities.InfoRenda;
import com.br.var.solutions.application.service.useCase.CalculoIRUserCase;
import org.springframework.stereotype.Service;

@Service
public class CalculoIRUserCaseImpl implements CalculoIRUserCase {

    public InfoRenda calculoIR(double salario){
        return calculaImposto(salario);
    }

    //Regra: bsse de calculo é - Salario Bruto X aliquota - dedução
    private InfoRenda calculaImposto(double salario) {
//        log.info("iniciando o calculo do imposto de renda " + salario);

        InfoRenda renda = new InfoRenda();
        String novoSalarioCalculado;


        if (salario < 1903.98) {
            renda.setSalario(String.valueOf(salario));
            renda.setIR("Isento");
            renda.setAliquota("0");
            return renda;
        } else if (salario > 1903.98 && salario < 2826.65) {
            double aliquota = 0.075;
            double calculoIRF = 142.80 - ((salario * aliquota) / 100);
            double novoSalario = salario - calculoIRF;
            renda.setIR(String.valueOf(calculoIRF));
            renda.setAliquota(String.valueOf(aliquota));
            renda.setSalario(String.valueOf(novoSalario));
            return renda;

        } else if (salario > 2826.65 && salario < 3751.05) {
            double aliquota = 0.15;
            double calculoIRF = 354.83 - ((salario * aliquota) / 100);
            double novoSalario = salario - calculoIRF;
            renda.setIR(String.valueOf(calculoIRF));
            renda.setAliquota(String.valueOf(aliquota));
            renda.setSalario(String.valueOf(novoSalario));
            return renda;

        } else if (salario > 3751.05 && salario < 4664.68) {
            double aliquota = 0.225;
            double calculoIRF = 636.13 - ((salario * aliquota) / 100);
            double novoSalario = salario - calculoIRF;
            renda.setIR(String.valueOf(calculoIRF));
            renda.setAliquota(String.valueOf(aliquota));
            renda.setSalario(String.valueOf(novoSalario));
            return renda;
        } else {
            double aliquota = 0.275;
            double calculoIRF = 869.36 - ((salario * aliquota) / 100);
            double novoSalario = salario - calculoIRF;
            renda.setIR(String.valueOf(calculoIRF));
            renda.setAliquota(String.valueOf(aliquota));
            renda.setSalario(String.valueOf(novoSalario));
            return renda;
        }
    }

}
