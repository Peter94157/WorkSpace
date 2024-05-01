package com.br.varsolutions.infraestructure.config.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProdutorMensagem {

    private final RabbitTemplate rabbitTemplate;
    public static final String NOME_EXCHANGE = "enviarEmail";
    public static final String ROUTING_KEY = "chaveEmail";
    private final Queue queue;
    public ProdutorMensagem(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void enviarEmail(String mensagem){
        rabbitTemplate.convertAndSend(ROUTING_KEY,mensagem);
        rabbitTemplate.setExchange(NOME_EXCHANGE);
        System.out.println("Mensagem enviada com sucesso "+mensagem);

    }

}
