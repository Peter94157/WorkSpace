package com.br.var.solutions.application.service.useCase;

import com.br.var.solutions.application.service.Entities.InfoIMC;

public interface ImcUseCase {
    InfoIMC calculoIMC(double peso, double altura);
}
