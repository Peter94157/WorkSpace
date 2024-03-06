package com.br.var.solutions.application.service.impl;

import com.br.var.solutions.application.service.Entities.InfoIMC;
import com.br.var.solutions.application.service.Entities.InfoRenda;
import com.br.var.solutions.adapters.input.Entities.PessaoResponse;
import com.br.var.solutions.adapters.input.Entities.Pessoa;
import com.br.var.solutions.application.service.useCase.MontarResponseFrontUserCase;
import org.springframework.stereotype.Service;

@Service
public class MontarResponseFrontUserCaseImpl implements MontarResponseFrontUserCase {

    public PessaoResponse MontarReponse(Pessoa pessoa, InfoIMC imc, int anoNasc, InfoRenda impostoRenda, String time, String saldoEmDolar){
        return montarRespostaFront(pessoa, imc, anoNasc, impostoRenda, time, saldoEmDolar);
    }

    private PessaoResponse montarRespostaFront(Pessoa pessoa, InfoIMC imc, int anoNasc, InfoRenda impostoRenda, String time, String saldoEmDolar) {
        PessaoResponse response = new PessaoResponse();

        response.setNome(pessoa.getNome());
        response.setIR(impostoRenda.getIR());
        response.setAliquota(impostoRenda.getAliquota());
        response.setSalario(impostoRenda.getSalario());
        response.setAnoNasc(anoNasc);
        response.setValidaMundial(time);
        response.setSaldoEmDolar(saldoEmDolar);
        response.setImc(imc.getImc());
        response.setClassificacaoIMC(imc.getClassificacao());
        response.setIdade(pessoa.getIdade());
        response.setTime(pessoa.getTime());
        response.setSobrenome(pessoa.getSobrenome());
        response.setEndereco(pessoa.getEndereco());
        response.setAltura(pessoa.getAltura());
        response.setPeso(pessoa.getPeso());
        response.setSaldo(pessoa.getSaldo());
        return response;
    }
}
