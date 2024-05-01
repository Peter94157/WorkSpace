package com.br.varsolutions.adapters.input.controllers;
import com.br.varsolutions.adapters.input.Entities.PessaoResponse;
import com.br.varsolutions.adapters.input.Entities.Pessoa;
import com.br.varsolutions.application.services.Entities.InfoIMC;
import com.br.varsolutions.application.services.useCase.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
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
    public ResponseEntity<List<PessaoResponse>> get() throws SQLException {

        List<PessaoResponse> listaDePessoas = montarResponseFrontUserCase.buscaListaPessoas();
        if(Objects.isNull(listaDePessoas)){
            return (ResponseEntity<List<PessaoResponse>>) ResponseEntity.notFound();
        }

        return ResponseEntity.ok(listaDePessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessaoResponse> getPessoa(@PathVariable Long id) throws SQLException {
        int idd = (int) (long) id;
        PessaoResponse detalhesPessoa = montarResponseFrontUserCase.buscaDetalhesPessoa(idd);

        if(Objects.isNull(detalhesPessoa)){
            return (ResponseEntity<PessaoResponse>) ResponseEntity.notFound();
        }
        return ResponseEntity.ok(detalhesPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessaoResponse> deletePessoa(@PathVariable Long id) throws SQLException {
        int idd = (int) (long) id;
        montarResponseFrontUserCase.excluirPessoa(idd);
        return ResponseEntity.status(HttpStatus.OK).body(PessaoResponse.builder().build());
    }

    @PostMapping("/resumo")
    public ResponseEntity<Object> getPessoa(@RequestBody Pessoa pessoinha) throws SQLException, JsonProcessingException {
        InfoIMC imc = InfoIMC.builder().build();
        int anoNasc = 0;
        double impostoRenda =0;
        String time = null;
        double saldoEmDolar = 0;

        if (!pessoinha.getNome().isEmpty()) {

            log.info("Iniciando o prccesso de resumo da pessoa ", pessoinha);

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
//            if (Boolean.TRUE.equals(desejaValidarMundial)) {
//                if (Objects.nonNull(pessoinha.getTime())) {
//                    log.info("Iniciando vendo qual o time");
//                    time = mundialUseCase.calculoMundial(pessoinha.getTime());
//                }
//            }
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
}