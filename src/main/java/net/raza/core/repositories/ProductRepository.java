package net.raza.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.raza.core.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	@Query("SELECT product "
			+ " FROM Product product " 
			+ " WHERE product.id IN :productIds")
	public List<Product> findByIds(@Param("productIds") List<Long> productIds);
	
}
