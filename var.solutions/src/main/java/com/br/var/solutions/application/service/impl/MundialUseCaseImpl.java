package com.br.var.solutions.application.service.impl;

import com.br.var.solutions.application.service.useCase.MundialUseCase;
import org.springframework.stereotype.Service;

@Service
public class MundialUseCaseImpl implements MundialUseCase {
    public String calculoMundial(String time){
        return qualTime(time);
    }
    private String qualTime(String time) {
        if (time.equalsIgnoreCase("Parma")) {
            return "Parabens você tem 1 mundial";
        } else {
            return "Que pena, não quero saber";
        }
    }
}
