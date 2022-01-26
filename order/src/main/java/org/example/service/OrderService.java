package org.example.service;

import org.example.model.Order;
import org.example.model.OrderRequest;

import java.util.List;

public interface OrderService {

    void create(OrderRequest orderRequest);
    Order getOrderById(Long id);
    List<Order> getAllOrder();




}
