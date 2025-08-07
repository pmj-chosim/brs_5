package com.example.demo.repository.ports;

import com.example.demo.repository.IOutputPort;
import com.example.demo.repository.entity.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepositoryPort extends IOutputPort {
    Optional<Order> findById(Long orderId);
    List<Order> findByCustomerId(Long customerId);
    Order save(Order order);
}
