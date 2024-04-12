package com.br.varsolutions.application.services.impl;

import com.br.varsolutions.adapters.input.Entities.PessaoResponse;
import com.br.varsolutions.adapters.input.Entities.Pessoa;
import com.br.varsolutions.application.services.Entities.InfoIMC;
import com.br.varsolutions.application.services.useCase.MontarResponseFrontUserCase;
import com.br.varsolutions.domain.entities.PessoaEntity;
import com.br.varsolutions.domain.entities.UsuarioEntity;
import com.br.varsolutions.domain.repositories.PessoaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
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
    public List<PessaoResponse> buscaListaPessoas() throws SQLException {
        return listPessoas();
    }
    private List<PessaoResponse> listPessoas() throws SQLException {

        try{
            List<PessoaEntity> pessoasEntity = repository.findAll();
            List<PessaoResponse> pessoasResponse = new ArrayList<>();

            pessoasEntity.stream().forEach(Entity -> {
                PessaoResponse pessoaResponse = PessaoResponse.builder().build();

                pessoaResponse.setUserId(Entity.getId());
                pessoaResponse.setNome(Entity.getNome());
                pessoaResponse.setDtNascimento(Entity.getDtNascimento());
                pessoaResponse.setAltura(Entity.getAltura());
                pessoaResponse.setPeso(Entity.getPeso());
                pessoaResponse.setSalario(Entity.getSalario());
                pessoaResponse.setSaldo(Entity.getSaldo());
                pessoaResponse.setIdade(Entity.getIdade());
                pessoaResponse.setImc(Entity.getImc());
                pessoaResponse.setClassificacaoIMC(Entity.getClassificacao());
                pessoaResponse.setIR(Entity.getInss());//Não colocou
                pessoaResponse.setAliquota(Entity.getAliquota());
//                pessoaResponse.setSalario(Entity.getSalarioLiquido());
                pessoaResponse.setSaldoEmDolar(Entity.getSaldoDolar());



                pessoasResponse.add(pessoaResponse);
            });
            return pessoasResponse;

        }
        catch(Exception e){
            throw new SQLException("Erro ao buscar no banco de dados", e);
        }

    }

    public PessaoResponse buscaDetalhesPessoa(int id) throws SQLException {

        return buscaDetalhesDaPessoa(id);
    }

    private PessaoResponse buscaDetalhesDaPessoa(int id) throws SQLException {

            PessoaEntity pessoasEntity = repository.findById(id);
        try{
            return PessaoResponse.builder()
                    .UserId(pessoasEntity.getId())
                    .nome(pessoasEntity.getNome())
                    .dtNascimento(pessoasEntity.getDtNascimento())
                    .altura(pessoasEntity.getAltura())
                    .peso(pessoasEntity.getPeso())
                    .salario(pessoasEntity.getSalario())
                    .saldo(pessoasEntity.getSaldo())
                    .idade(pessoasEntity.getIdade())
                    .imc(pessoasEntity.getImc())
                    .classificacaoIMC(pessoasEntity.getClassificacao())
                    .IR(pessoasEntity.getInss())
                    .aliquota(pessoasEntity.getAliquota())
                    .saldoEmDolar(pessoasEntity.getSaldoDolar())
        .build();
        }catch(Exception e){
            throw new SQLException("Não foi possivel encontrar os detalhes no Banco de dados",e);
        }
    }
    public void excluirPessoa(int id) throws SQLException {

        try{
            repository.deleteById(id);
        }
        catch (Exception e){
            throw new SQLException("Não foi possivel Excluir esse id",e);
        }


    }
}
