package net.raza.core.managers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.raza.core.models.Product;
import net.raza.core.services.ProductService;

@Controller
public class ProductManager {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Example entity management operation.
     */
    public BigDecimal checkForDiscounts(Long productId, String promotionalCode) {
		
    	Map<String, BigDecimal> activePromotionalCodes = new HashMap<String, BigDecimal>();
    	activePromotionalCodes.put("vip", new BigDecimal("0.1"));
    	activePromotionalCodes.put("christmas", new BigDecimal("0.3"));
    	
    	BigDecimal discountFactor = activePromotionalCodes.get("promotionalCode");
    	
    	Product product = productService.getProductById(productId);
    	BigDecimal finalPrice = product.getPrice().multiply(BigDecimal.ONE.subtract(discountFactor));
    	
    	return finalPrice;
    }

}
