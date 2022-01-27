package org.example.repository;

import org.example.model.OrderProduct;
import org.example.model.OrderProductResponse;
import org.example.model.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {

    @Query(nativeQuery = true)
    List<OrderProductResponse> findOrderProductResponseByOrderId(Long id);

}
