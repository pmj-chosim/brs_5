package com.example.demo.service.usecases;

import com.example.demo.repository.entity.Product;
import com.example.demo.service.IInputPort;

import java.util.List;

public interface IDisplayProductUseCase extends IInputPort {
    List<Product> getProducts(String keyword);
    Product getProductById(Long productId);
}
