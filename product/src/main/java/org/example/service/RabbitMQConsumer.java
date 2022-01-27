package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.model.ProductRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = "${product.rabbitmq.queue}")
    public void recievedMessage(List<ProductRequest> productRequests) {
        System.out.println("Recieved Message From RabbitMQ: " + productRequests);
    }
}