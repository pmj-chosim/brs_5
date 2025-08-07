package com.example.demo.service.usecases;

import com.example.demo.repository.entity.Cart;
import com.example.demo.service.IInputPort;

import java.util.Optional;

public interface IManageCartUseCase extends IInputPort {
     void addProductToCart(Long customerId, Long productId, Long optionId, int quantity);
     Optional<Cart> getCart(Long customerId);
}
