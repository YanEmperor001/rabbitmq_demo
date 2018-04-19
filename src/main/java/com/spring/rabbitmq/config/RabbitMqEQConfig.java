package com.spring.rabbitmq.config;

import com.spring.rabbitmq.enums.RabbitMqEnum;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@AutoConfigureAfter(RabbitMqConfig.class)
public class RabbitMqEQConfig {

    /**
     * @author guofeng
     * @create 2018/4/18
     * @Description 点-点
     **/
    @Bean
    public DirectExchange directExchange() {
        DirectExchange directExchange = new DirectExchange(RabbitMqEnum.Exchage.CONTRACT_DIRECT.getCode());
        return directExchange;
    }

    /**
     * @author guofeng
     * @create 2018/4/18
     * @Description 订阅-发布
     **/
    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange(RabbitMqEnum.Exchage.CONTRACT_TOPIC.getCode());
        return topicExchange;
    }

    /**
     * @author guofeng
     * @create 2018/4/18
     * @Description 普通队列
     **/
    @Bean
    public Queue queue() {
        Queue queue = new Queue(RabbitMqEnum.Queue.TESTQUEUE.getCode());
        return queue;
    }

    /**
     * @author guofeng@incarcar.com
     * @create 2018/4/18
     * @Description 订阅/发布队列
     **/
    @Bean
    public Queue topicQueue() {
        Queue queue = new Queue(RabbitMqEnum.Queue.TOPICTEST.getCode());
        return queue;
    }

    /**
     * @author guofeng@
     * @create 2018/4/18
     * @Description 普通队列绑定交换机
     **/
    @Bean
    public Binding bindingQueue(Queue queue, DirectExchange directExchange) {
        Binding binding = BindingBuilder.bind(queue).to(directExchange).with(RabbitMqEnum.QueueKey.TESTQUEUE.getCode());
        return binding;
    }

    /**
     * @author guofeng
     * @create 2018/4/18
     * @Description 订阅/发布队列绑定交换机
     **/
    @Bean
    public Binding bindingTopicQueue(Queue queue, TopicExchange topicExchange) {
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqEnum.QueueKey.TESTTOPICQUEUE.getCode());
        return binding;
    }
}
