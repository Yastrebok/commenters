package com.fill.commenter.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class Producer {


    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing}")
    private String routingKey;

    @Value("${rabbitmq.queue}")
    private String queue;

    @Bean
    public Queue queue(){
        return new Queue(queue, false);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

}
