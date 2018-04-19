package com.spring.rabbitmq.service.impl;

import com.spring.rabbitmq.enums.RabbitMqEnum;
import com.spring.rabbitmq.sender.RabbitMqSender;
import com.spring.rabbitmq.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rabbitMqService")
public class RabbitMqServiceImpl implements RabbitMqService {

    @Autowired
    private RabbitMqSender rabbitMqSender;

    @Override
    public void sendMsg() {
        rabbitMqSender.sendDirectMsg(RabbitMqEnum.QueueKey.TESTQUEUE.getCode(), "测试！");
        rabbitMqSender.sendTopicMsg(RabbitMqEnum.QueueKey.TESTTOPICQUEUE.getCode(), "测试topic!");
    }
}
