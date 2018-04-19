package com.spring.rabbitmq.listener;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.spring.rabbitmq.config.RabbitMqConfig;
import com.spring.rabbitmq.enums.RabbitMqEnum;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class TraditionMonitorListener {

    private final Logger logger = Logger.getLogger(TraditionMonitorListener.class);

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitMqEnum.Queue.TESTQUEUE.getCode());
        container.setMessageListener(channelAwareMessageListener());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }

    @Bean
    public ChannelAwareMessageListener channelAwareMessageListener() {
        return new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                try {
                    //获取消息体
                    String body = new String(message.getBody(), "UTF-8");
                    logger.info("消息队列：" + message.getMessageProperties().getReceivedRoutingKey()+ "\t确认消息内容：" + body);
                    //消息确认
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                } catch (Exception e) {
                    logger.info("处理消息异常：" + e);
                    //出现异常直接拒绝，不在放入队列
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                }
            }
        };
    }
}
