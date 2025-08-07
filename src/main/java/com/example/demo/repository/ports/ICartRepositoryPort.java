package com.example.demo.repository.ports;

import com.example.demo.repository.IOutputPort;
import com.example.demo.repository.entity.Cart;

import java.util.Optional;

public interface ICartRepositoryPort extends IOutputPort {
    Optional<Cart> findByOptionalId(Long customerId);
    Cart save(Cart cart);
}
