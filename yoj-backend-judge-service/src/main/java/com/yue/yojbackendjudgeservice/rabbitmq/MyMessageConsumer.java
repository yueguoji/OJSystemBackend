package com.yue.yojbackendjudgeservice.rabbitmq;

import com.rabbitmq.client.Channel;
import com.yue.yojbackendjudgeservice.judge.JudegeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息队里消费者
 */
@Component
@Slf4j
public class MyMessageConsumer {

    @Resource
    private JudegeService judegeService;

    // 指定程序监听的消息队列和确认机制 MANUAL手动确认机制
    @SneakyThrows
    @RabbitListener(queues = {"code_queue"}, ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        log.info("receiveMessage message = {}", message);
        long questionSubmitId = Long.parseLong(message);
        try {
            //消费消息
            judegeService.dojudge(questionSubmitId);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            channel.basicNack(deliveryTag, false, false);
        }
    }

}
