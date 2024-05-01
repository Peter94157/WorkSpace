package com.br.varsolutions.application.services.useCase;

import com.br.varsolutions.application.services.Entities.InfoIMC;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ImcUseCase {
    InfoIMC calculoIMC(double peso, double altura) throws JsonProcessingException;
}
