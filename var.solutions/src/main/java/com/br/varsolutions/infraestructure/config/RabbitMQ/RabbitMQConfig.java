package com.br.varsolutions.infraestructure.config.RabbitMQ;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String NOME_FILA ="RecuperacaoDeSenha";
    public static final String HOST ="localhost";
    public static final int PORTA =5672;
    public static final String USUARIO ="guest";
    public static final String SENHA ="guest";



    @Bean
    public Queue queue(){
        return new Queue(NOME_FILA,false);
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(HOST,PORTA);
        connectionFactory.setUsername(USUARIO);
        connectionFactory.setPassword(SENHA);

        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMensageConverter());

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMensageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
