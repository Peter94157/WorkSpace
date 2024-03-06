package com.br.var.solutions.adapters.input.controllers;
import com.br.var.solutions.adapters.input.Entities.PessaoResponse;
import com.br.var.solutions.adapters.input.Entities.Pessoa;
import com.br.var.solutions.application.service.Entities.InfoIMC;
import com.br.var.solutions.application.service.Entities.InfoRenda;
import com.br.var.solutions.application.service.useCase.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = "*")
@Slf4j
public class pessoaControler {
    @Autowired
    private MontarResponseFrontUserCase montarResponseFrontUserCase;
    @Autowired
    private ConverterDolarUserCase converterDolarUserCase;
    @Autowired
    private CalculoIRUserCase calculoIRUserCase;
    @Autowired
    private AnoNascimentoUserCase anoNascimentoUserCase;
    @Autowired
    private MundialUseCase mundialUseCase;
    @Autowired
    private ImcUseCase imcUseCase;
    //EndPoint
    @GetMapping
    public ResponseEntity<Object> get() {
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Pedro");
        pessoa1.setSobrenome("Leonardo  ");
        pessoa1.setEndereco("Estrada do pequia,286");
        pessoa1.setIdade(22);
        return ResponseEntity.ok(pessoa1);
    }
    @GetMapping("/resumo")
    public ResponseEntity<Object> getPessoa(@RequestBody Pessoa pessoinha, @RequestParam(value = "Valida_Mundial") Boolean desejaValidarMundial) {
        InfoIMC imc = InfoIMC.builder().build();
        int anoNasc = 0;
        InfoRenda impostoRenda = new InfoRenda();
        String time = null;
        String saldoEmDolar = null;

        if (!pessoinha.getNome().isEmpty()) {

            log.info("Iniciando o prccessp de resumo da pessoa ", pessoinha);

            if (Objects.nonNull(pessoinha.getAltura()) && Objects.nonNull(pessoinha.getPeso())) {
                log.info("Iniciando calcullo IMC");
                imc = imcUseCase.calculoIMC(pessoinha.getPeso(), pessoinha.getAltura());
            }
            if (Objects.nonNull(pessoinha.getIdade())) {
                log.info("Iniciando calcula Idade");
                anoNasc = anoNascimentoUserCase.calculoAnoNasc(pessoinha.getIdade());
            }

            if (Objects.nonNull(pessoinha.getSalario())) {
                log.info("Iniciando calcula salario");
                impostoRenda = calculoIRUserCase.calculoIR(pessoinha.getSalario());
            }
            if (Boolean.TRUE.equals(desejaValidarMundial)) {
                if (Objects.nonNull(pessoinha.getTime())) {
                    log.info("Iniciando vendo qual o time");
                    time = mundialUseCase.calculoMundial(pessoinha.getTime());
                }
            }
            if (Objects.nonNull(pessoinha.getSaldo())) {
                log.info("Inciando o calculo da conversão");
                saldoEmDolar = converterDolarUserCase.converterDolar(pessoinha.getSaldo());
            }

            log.info("montando objeto de retorno para o Front end");
            PessaoResponse resumo = montarResponseFrontUserCase.MontarReponse(pessoinha, imc, anoNasc, impostoRenda, time, saldoEmDolar);
            return ResponseEntity.ok(resumo);
        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    public void retornoDelete() {
    }
    @PutMapping
    public void retornoPut() {
    }
    @PostMapping
    public void retornoPost() {
    }
}