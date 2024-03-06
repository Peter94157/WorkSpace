package com.br.var.solutions.application.service.useCase;

import com.br.var.solutions.application.service.Entities.InfoIMC;
import com.br.var.solutions.application.service.Entities.InfoRenda;
import com.br.var.solutions.adapters.input.Entities.PessaoResponse;
import com.br.var.solutions.adapters.input.Entities.Pessoa;

public interface MontarResponseFrontUserCase {
    PessaoResponse MontarReponse(Pessoa pessoa, InfoIMC imc, int anoNasc, InfoRenda impostoRenda, String time, String saldoEmDolar);
}
