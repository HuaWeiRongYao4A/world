package com.world.producer.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/7/9.
 */
@Configuration
public class RabbitmqEmailConfig {
    @Bean
    public Queue queue() {
        return new Queue("email", true);
    }

    @Bean
    public TopicExchange TopicExchange() {
        return new TopicExchange("emailExchange");
    }

    @Bean
    Binding bindingTopicExchange(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("topic.user.register.email.key");
    }
}
