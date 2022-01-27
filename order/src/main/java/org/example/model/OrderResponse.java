package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {

    private Long id;

    private String username;

    private String phoneNumber;

    private List<OrderProductResponse> orderProducts;

}
