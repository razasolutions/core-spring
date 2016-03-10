package net.raza.core.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.raza.core.models.User;
import net.raza.core.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}
	
	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
