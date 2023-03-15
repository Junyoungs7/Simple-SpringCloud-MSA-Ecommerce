package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.model.Order;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<Order> getOrdersByUserId(String userId);
}
