package com.br.varsolutions.application.services.impl;

import com.br.varsolutions.application.services.Entities.InfoRenda;
import com.br.varsolutions.application.services.useCase.CalculoIRUserCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculoIRUserCaseImpl implements CalculoIRUserCase {

    public double calculoIR(double salario){
        return calculaImposto(salario);
    }

    //Regra: bsse de calculo é - Salario Bruto X aliquota - dedução
    private double calculaImposto(double salario) {
        log.info("iniciando o calculo do imposto de renda " + salario);

        double novoSalarioCalculado;

        if (salario < 1903.98) {
            return salario;
        } else if (salario > 1903.98 && salario < 2826.65) {
            double aliquota = 0.075;
            double calculoIRF = 142.80 - ((salario * aliquota) / 100);
            double novoSalario = salario - calculoIRF;
            novoSalarioCalculado = novoSalario;
            return novoSalarioCalculado;

        } else if (salario > 2826.65 && salario < 3751.05) {
            double aliquota = 0.15;
            double calculoIRF = 354.83 - ((salario * aliquota) / 100);
            double novoSalario = salario - calculoIRF;
            novoSalarioCalculado = novoSalario;
            return novoSalarioCalculado;

        } else if (salario > 3751.05 && salario < 4664.68) {
            double aliquota = 0.225;
            double calculoIRF = 636.13 - ((salario * aliquota) / 100);
            double novoSalario = salario - calculoIRF;
            novoSalarioCalculado = novoSalario;
            return novoSalarioCalculado;
        } else {
            double aliquota = 0.275;
            double calculoIRF = 869.36 - ((salario * aliquota) / 100);
            double novoSalario = salario - calculoIRF;
            novoSalarioCalculado = novoSalario;
            return novoSalarioCalculado;
        }
    }

}
