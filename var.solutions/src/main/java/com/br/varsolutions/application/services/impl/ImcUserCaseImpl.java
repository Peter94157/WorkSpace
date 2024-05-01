package com.br.varsolutions.application.services.impl;

import com.br.varsolutions.adapters.output.useCase.RedisUseCase;
import com.br.varsolutions.application.services.Entities.InfoIMC;
import com.br.varsolutions.application.services.useCase.ImcUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ImcUserCaseImpl implements ImcUseCase {

    @Autowired
    RedisUseCase redisUseCase;

    public InfoIMC calculoIMC(double peso, double altura) throws JsonProcessingException {
        return calculateImc(peso, altura);
    }



    private InfoIMC calculateImc(double peso, double altura) throws JsonProcessingException {
        double imc = Double.parseDouble(new DecimalFormat("#,##").format(peso / (altura * altura)));
        int hashKey= String.valueOf(peso).hashCode()+String.valueOf(altura).hashCode();
        ObjectMapper objectMapper = new ObjectMapper();

        String retornoImcRedis = redisUseCase.recuperarDadosRedis(hashKey);

        if(retornoImcRedis!=null){
            var imcRedis = objectMapper.readValue(retornoImcRedis, InfoIMC.class);
            return imcRedis;
        }

        if (imc < 18.5) {

            var imcCalculado = InfoIMC.builder()
                    .imc(imc)
                    .classificacao("Abaixo do peso")
                    .build();
            String imcRedisValor = objectMapper.writeValueAsString(imcCalculado);
            redisUseCase.salvarDadosNoRedis(hashKey,imcRedisValor,120);
            return imcCalculado;

        } else if (imc >= 18.5 && imc <= 24.9) {
            var imcCalculado = InfoIMC.builder()
                    .imc(imc)
                    .classificacao("Peso Normal")
                    .build();
            String imcRedisValor = objectMapper.writeValueAsString(imcCalculado);
            redisUseCase.salvarDadosNoRedis(hashKey,imcRedisValor,120);
            return imcCalculado;
        } else if (imc > 24.9 && imc <= 29.9) {
            var imcCalculado = InfoIMC.builder()
                    .imc(imc)
                    .classificacao("Excesso de peso")
                    .build();
            String imcRedisValor = objectMapper.writeValueAsString(imcCalculado);
            redisUseCase.salvarDadosNoRedis(hashKey,imcRedisValor,120);
            return imcCalculado;
        } else if (imc > 29.9 && imc <= 34.9) {
            var imcCalculado = InfoIMC.builder()
                    .imc(imc)
                    .classificacao("Obessidade I")
                    .build();
            String imcRedisValor = objectMapper.writeValueAsString(imcCalculado);
            redisUseCase.salvarDadosNoRedis(hashKey,imcRedisValor,120);
            return imcCalculado;
        } else if (imc > 34.9 && imc <= 39.9) {
            var imcCalculado = InfoIMC.builder()
                    .imc(imc)
                    .classificacao("Obessidade II")
                    .build();
            String imcRedisValor = objectMapper.writeValueAsString(imcCalculado);
            redisUseCase.salvarDadosNoRedis(hashKey,imcRedisValor,120);
            return imcCalculado;
        } else {
            var imcCalculado = InfoIMC.builder()
                    .imc(imc)
                    .classificacao("Obessidade III")
                    .build();
            String imcRedisValor = objectMapper.writeValueAsString(imcCalculado);
            redisUseCase.salvarDadosNoRedis(hashKey,imcRedisValor,120);
            return imcCalculado;
        }
    }
}
