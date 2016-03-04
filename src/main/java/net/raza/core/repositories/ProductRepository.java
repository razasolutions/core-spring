package net.raza.core.repositories;

import org.springframework.data.repository.CrudRepository;

import net.raza.core.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
