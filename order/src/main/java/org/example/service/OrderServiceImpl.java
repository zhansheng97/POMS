package org.example.service;

import org.example.dto.ProductDto;
import org.example.exception.OrderNotFoundException;
import org.example.model.*;
import org.example.repository.OrderProductRepository;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements  OrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public void create(OrderRequest orderRequest) {
        System.out.println(orderRequest);
        // Save Product Detail into db
        List<Product> productList = getPoductDetail(orderRequest.getProducts());
        productRepository.saveAll(productList);

        // Save Order Into db
        Order order = createOrder(orderRequest, productList);
        orderRepository.save(order);

        // Save OrderProduct Into Db
        List<OrderProduct> orderProductList = createOrderProducts(orderRequest, productList, order);

        orderProductRepository.saveAll(orderProductList);
    }

    private List<OrderProduct> createOrderProducts(OrderRequest orderRequest, List<Product> productList, Order order) {
        List<OrderProduct> orderProductList = new ArrayList<>();

        for (Product product: productList) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            for (ProductRequest productRequest: orderRequest.getProducts()) {
                if (product.getProductCode().equalsIgnoreCase(productRequest.getProductCode())) {
                    orderProduct.setQuantity(productRequest.getQuantity());
                }
            }
            orderProductList.add(orderProduct);
        }
        return orderProductList;
    }

    private Order createOrder(OrderRequest orderRequest, List<Product> productList) {
        Order order = new Order();
        order.setUsername(orderRequest.getUsername());
        order.setPhoneNumber(orderRequest.getPhoneNumber());

        // Get TotalPrice of each Order
        double totalPrice = 0.0;
        for (Product product: productList) {
            for (ProductRequest productRequest: orderRequest.getProducts()) {
                if (product.getProductCode().equals(productRequest.getProductCode())) {
                    double productPrice = product.getPrice().doubleValue() ;
                    totalPrice += productPrice * productRequest.getQuantity();
                }
            }
        }

        order.setTotalPrice(new BigDecimal(totalPrice));
        return order;
    }

    private List<Product> getPoductDetail(List<ProductRequest> productRequestList) {
        List<Product> products =new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        for (ProductRequest productRequest: productRequestList) {
            ProductDto productDto = restTemplate.getForObject("http://localhost:8081/api/v1/product/" + productRequest.getProductCode(), ProductDto.class);
            Product product = new Product();
            product.setId(productDto.getId());
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setProductCode(productDto.getProductCode());
            product.setPrice(productDto.getPrice());
            products.add(product);
        }
        return products;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(String.format("Order with id %o not found", id)));
        OrderResponse orderResponse = getOrderResponseByOrderId(order,id);
        return orderResponse;

    }

    private OrderResponse getOrderResponseByOrderId(Order order, Long id) {
        List<OrderProductResponse> orderProductResponsesList = orderProductRepository.findOrderProductResponseByOrderId(id);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUsername(order.getUsername());
        orderResponse.setPhoneNumber(order.getPhoneNumber());
        orderResponse.setOrderProducts(orderProductResponsesList);
        return orderResponse;
    }

    @Override
    public List<OrderResponse>getAllOrder() {
        List<Order> allOrders = orderRepository.findAll();
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order order : allOrders) {
            OrderResponse or = getOrderResponseByOrderId(order, order.getId());
            orderResponseList.add(or);
        }
        return orderResponseList;

    }

}
