package net.raza.core.services;


import org.springframework.stereotype.Component;

import net.raza.core.models.User;

@Component
public interface UserService {
	
    Iterable<User> findAll();

    User findById(Long id);

    User save(User user);
    
}
