package com.act.cooperativism.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.name}")
    String nomeFila;

    @Value("$rabbitmq.exchange.name}")
    String nomeIntercambio;

    @Value("${rabbitmq.routing.key}")
    private String idRota;
    
    @Bean
    Queue queue() {
        return new Queue(nomeFila, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(nomeIntercambio);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(idRota);
    }
    
    @Bean
    public MessageConverter messageConverter()
    {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        return new Jackson2JsonMessageConverter(mapper);
    }
}
