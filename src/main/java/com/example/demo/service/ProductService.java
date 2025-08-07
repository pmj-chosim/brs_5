package com.example.demo.service;

import com.example.demo.repository.entity.Product;
import com.example.demo.repository.ports.IProductRepositoryPort;
import com.example.demo.service.usecases.IDisplayProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IDisplayProductUseCase {

    private final IProductRepositoryPort productRepository;

    @Override
    public List<Product> getProducts(String keyword) {
        List<Product> products = StringUtils.hasText(keyword)
                ? productRepository.findByKeyword(keyword)
                : productRepository.findAll();
        return products.stream()
                .filter(Product::hasStock)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(()->new IllegalArgumentException("상품을 찾을 수 없습니다"));
    }
}
