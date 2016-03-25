package net.raza.core.services;


import org.springframework.stereotype.Service;

import net.raza.core.models.User;

@Service
public interface UserService {
	
    Iterable<User> findAll();
    
    User findById(Long id);
    
    User findByUsername(String userName);
    
    User save(User user);
    
	void delete(User user);

	void delete(Long id);

	User update(User user);
	
	void flush();
	
	Long count();
	
	public boolean invalidUser(User user);
    
}
