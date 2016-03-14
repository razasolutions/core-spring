package net.raza.core.services;


import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import net.raza.core.models.Product;

@Service
public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Long id);

    Product saveProduct(Product product);

	BigDecimal checkForDiscounts(Long productId, String promotionalCode);
}
