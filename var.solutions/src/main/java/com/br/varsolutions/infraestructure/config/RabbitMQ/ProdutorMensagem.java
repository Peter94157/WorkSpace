package com.br.varsolutions.infraestructure.config.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PublicarMensagem {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    public PublicarMensagem(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void publicarMensagem(String mensagem){
        rabbitTemplate.con

    }

}
