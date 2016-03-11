package net.raza.core.services;


import org.springframework.stereotype.Component;

import net.raza.core.models.User;

@Component
public interface UserService {
	
    Iterable<User> findAll();

    User findById(Long id);
    
    User findByUsername(String userName);
    
    User save(User user);

	void delete(User user);

	void delete(Long id);
    
}
