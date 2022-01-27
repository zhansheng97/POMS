package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.model.ProductRequest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${product.rabbitmq.exchange}")
    private String exchange;

    @Value("${product.rabbitmq.routingkey}")
    private String routingkey;

    public void send(List<ProductRequest> productList) {
        rabbitTemplate.convertAndSend(exchange, routingkey, productList);
        log.info("Send Message to RabbitMQ = " + productList);
    }
}