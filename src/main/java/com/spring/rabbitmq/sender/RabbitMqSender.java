package com.spring.rabbitmq.sender;

import com.spring.rabbitmq.enums.RabbitMqEnum;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMqSender implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = Logger.getLogger(RabbitMqSender.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("确认：" + correlationData.getId());
    }

    /**
     * @author guofeng
     * @create 2018/4/18
     * @Description 点-点消息发送接口
     **/
    public void sendDirectMsg(String routeKey, Object msg) {
        logger.info("发送队列：" + routeKey + "\t信息内容：" + msg);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replace("-", ""));
        this.rabbitTemplate.convertAndSend(RabbitMqEnum.Exchage.CONTRACT_DIRECT.getCode(), routeKey, msg, correlationData);
    }

    /**
     * @author guofeng
     * @create 2018/4/18
     * @Description 订阅-发布消息发送接口
     **/
    public void sendTopicMsg(String routeKey, Object msg) {
        logger.info("topic发送队列：" + routeKey + "\ttopic信息内容：" + msg);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replace("-", ""));
        this.rabbitTemplate.convertAndSend(RabbitMqEnum.Exchage.CONTRACT_TOPIC.getCode(), routeKey, msg, correlationData);
    }
}
