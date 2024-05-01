package com.br.varsolutions.adapters.output.useCase;

public interface RedisUseCase {
    void salvarDadosNoRedis(int chave, String valor, long tempoDeExpiracaoEmSegundos);

    String recuperarDadosRedis(int chave);
}
