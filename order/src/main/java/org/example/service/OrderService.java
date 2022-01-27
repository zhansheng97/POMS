package org.example.service;

import org.example.model.Order;
import org.example.model.OrderRequest;
import org.example.model.OrderResponse;

import java.util.List;

public interface OrderService {

    void create(OrderRequest orderRequest);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getAllOrder();




}
