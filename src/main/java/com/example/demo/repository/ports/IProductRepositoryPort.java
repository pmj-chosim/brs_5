package com.example.demo.repository.ports;

import com.example.demo.repository.IOutputPort;
import com.example.demo.repository.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepositoryPort extends IOutputPort {
    Optional<Product> findById(Long productId);
    List<Product> findAll();
    List<Product> findByKeyword(String keyword);
    Product save(Product product);
}
