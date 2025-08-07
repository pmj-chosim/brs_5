package com.example.demo.service;

import com.example.demo.repository.entity.Cart;
import com.example.demo.repository.entity.Product;
import com.example.demo.repository.ports.ICartRepositoryPort;
import com.example.demo.repository.ports.IProductRepositoryPort;
import com.example.demo.service.usecases.IManageCartUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService implements IManageCartUseCase {
    private final ICartRepositoryPort cartRepositoryPort;
    private final IProductRepositoryPort cartRepository;

    @Override
    public void addProductToCart(Long customerId, Long productId, Long optionId, int quantity) {
        Cart cart=cartRepository.findByCustomerId(customerId)
                .orElse(new Cart(customerId));

        Product product=productRepository.findById(productId)
                .orElseThrow(()->new IllegalAccessException("상품을 찾을 수 없습니다."));

        product.getOptionById(optionId)
                .ifPresentOrElse(
                        option -> {
                            cart.addProduct(product, option, quantity);
                            cartRepository.save(cart);
                        },
                        ()->{throw new IllegalArgumentException("상품 옵션을 찾을 수 없습니다");}
                );
    }

    @Override
    public Optional<Cart> getCart(Long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }
}
