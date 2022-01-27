package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Order;
import org.example.model.OrderProductResponse;
import org.example.model.OrderRequest;

import org.example.model.OrderResponse;
import org.example.repository.OrderProductRepository;
import org.example.service.OrderService;
import org.example.service.RabbitMQSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final RabbitMQSender rabbitMQSender;

    public OrderController(OrderService orderService, RabbitMQSender rabbitMQSender) {
        this.orderService = orderService;
        this.rabbitMQSender = rabbitMQSender;
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.create(orderRequest);
        log.info("Start Sending Message To RabbitMQ Product");
        rabbitMQSender.send(orderRequest.getProducts());
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable("orderId") Long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return orderResponse;
    }

    @GetMapping("/all")
    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderResponseList = orderService.getAllOrder();
        return orderResponseList;
    }

}
