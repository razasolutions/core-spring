package net.raza.core.loaders;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.raza.core.models.Product;
import net.raza.core.services.ProductService;

@Component
public class ProductLoader {

	@Autowired
	private ProductService productService;

	private Logger log = Logger.getLogger(ProductLoader.class);

	public void doLoad() {

		if (!productService.listAllProducts().iterator().hasNext()) {

			Product shirt = new Product();
			shirt.setDescription("Spring Framework Guru Shirt");
			shirt.setPrice(new BigDecimal("18.95"));
			shirt.setImageUrl(
					"https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
			shirt.setProductId("235268845711068308");
			productService.saveProduct(shirt);

			log.info("Saved Shirt - id: " + shirt.getId());

			Product mug = new Product();
			mug.setDescription("Spring Framework Guru Mug");
			mug.setImageUrl(
					"https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
			mug.setProductId("168639393495335947");
			mug.setPrice(new BigDecimal("11.95"));
			productService.saveProduct(mug);

			log.info("Saved Mug - id:" + mug.getId());
		}
	}
}
