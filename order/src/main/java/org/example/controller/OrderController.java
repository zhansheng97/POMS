package org.example.controller;

import org.example.model.Order;
import org.example.model.OrderProductResponse;
import org.example.model.OrderRequest;

import org.example.model.OrderResponse;
import org.example.repository.OrderProductRepository;
import org.example.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.create(orderRequest);
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
