package net.raza.core.services;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.raza.core.models.Product;
import net.raza.core.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
	/**
	 * Example entity management operation.
	 */
    @Override
	public BigDecimal checkForDiscounts(Long productId, String promotionalCode) {

		Map<String, BigDecimal> activePromotionalCodes = new HashMap<String, BigDecimal>();
		activePromotionalCodes.put("vip", new BigDecimal("0.1"));
		activePromotionalCodes.put("christmas", new BigDecimal("0.3"));

		BigDecimal discountFactor = activePromotionalCodes.get("promotionalCode");

		Product product = this.getProductById(productId);
		BigDecimal finalPrice = product.getPrice().multiply(BigDecimal.ONE.subtract(discountFactor));

		return finalPrice;
	}
}
