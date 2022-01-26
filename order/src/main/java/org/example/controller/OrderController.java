package org.example.controller;

import org.example.model.Order;
import org.example.model.OrderRequest;

import org.example.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Order getOrder(@PathVariable("orderId") Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return order;
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        List<Order> order = orderService.getAllOrder();
        System.out.println(order.size());

        return order;
    }


}
