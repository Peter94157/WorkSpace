package com.br.varsolutions.application.services.useCase;

import com.br.varsolutions.application.services.Entities.InfoIMC;

public interface ImcUseCase {
    InfoIMC calculoIMC(double peso, double altura);
}
