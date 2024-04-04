package com.br.varsolutions.application.services.impl;

import com.br.varsolutions.adapters.input.Entities.PessaoResponse;
import com.br.varsolutions.adapters.input.Entities.Pessoa;
import com.br.varsolutions.application.services.Entities.InfoIMC;
import com.br.varsolutions.application.services.Entities.InfoRenda;
import com.br.varsolutions.application.services.useCase.MontarResponseFrontUserCase;
import com.br.varsolutions.domain.entities.PessoaEntity;
import com.br.varsolutions.domain.entities.UsuarioEntity;
import com.br.varsolutions.domain.repositories.PessoaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

@Service
@Slf4j
public class MontarResponseFrontUserCaseImpl implements MontarResponseFrontUserCase {
    @Autowired
    PessoaRepository repository ;

    public PessaoResponse MontarReponse(Pessoa pessoa, InfoIMC imc, int anoNasc, double impostoRenda, String time, double saldoEmDolar) throws SQLException {
        return montarRespostaFront(pessoa, imc, anoNasc, impostoRenda, time, saldoEmDolar);
    }

    private PessaoResponse montarRespostaFront(Pessoa pessoa, InfoIMC imc, int anoNasc, double impostoRenda, String time, double saldoEmDolar) throws SQLException {



        try {

        PessaoResponse pessoaResponse = PessaoResponse.builder()
                .nome(pessoa.getNome())
                .salario(impostoRenda)
                .anoNasc(anoNasc)
                .validaMundial(time)
                .saldoEmDolar(saldoEmDolar)
                .imc(imc.getImc())
                .classificacaoIMC(imc.getClassificacao())
                .idade(pessoa.getIdade())
                .time(pessoa.getTime())
                .sobrenome(pessoa.getSobrenome())
                .endereco(pessoa.getEndereco())
                .altura(pessoa.getAltura())
                .peso(pessoa.getPeso())
                .saldo(pessoa.getSaldo())
                .IR(impostoRenda)
                .build();

        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setNome(pessoaResponse.getNome());
        pessoaEntity.setDtNascimento(LocalDate.now());
        pessoaEntity.setAltura(pessoaResponse.getAltura());
        pessoaEntity.setPeso(pessoaResponse.getPeso());
        pessoaEntity.setSalario(pessoaResponse.getSalario());
        pessoaEntity.setSaldo(pessoaResponse.getSaldo());
        pessoaEntity.setIdade(pessoaResponse.getIdade());
        pessoaEntity.setImc(pessoaResponse.getImc());
        pessoaEntity.setClassificacao(pessoaResponse.getClassificacaoIMC());
        pessoaEntity.setInss(pessoaResponse.getIR());
        pessoaEntity.setAliquota(pessoaResponse.getAliquota());
        pessoaEntity.setSalarioLiquido(pessoaResponse.getSalario());
        pessoaEntity.setSaldoDolar(pessoaResponse.getSaldoEmDolar());
        UsuarioEntity userEntity = new UsuarioEntity();
        userEntity.setId(pessoa.userId);
        pessoaEntity.setUsuario(userEntity);

        repository.save(pessoaEntity);
        log.info("Dados da pessoa salvo com sucesso");

        return pessoaResponse;
        }catch(Exception e) {
            throw new SQLException("Erro ao salvar no Banco de Dados", e);
        }




    }
}
