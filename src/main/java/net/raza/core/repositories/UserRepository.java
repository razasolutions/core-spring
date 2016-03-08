package net.raza.core.repositories;

import org.springframework.data.repository.CrudRepository;

import net.raza.core.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUserName(String userName);
	
}