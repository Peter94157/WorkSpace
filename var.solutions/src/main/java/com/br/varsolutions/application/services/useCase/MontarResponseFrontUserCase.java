package com.br.varsolutions.application.services.useCase;

import com.br.varsolutions.adapters.input.Entities.PessaoResponse;
import com.br.varsolutions.adapters.input.Entities.Pessoa;
import com.br.varsolutions.application.services.Entities.InfoIMC;
import com.br.varsolutions.application.services.Entities.InfoRenda;

import java.sql.SQLException;

public interface MontarResponseFrontUserCase {
    PessaoResponse MontarReponse(Pessoa pessoa, InfoIMC imc, int anoNasc, double impostoRenda, String time, double saldoEmDolar) throws SQLException;
}
