package net.raza.core.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import net.raza.core.models.User;
import net.raza.core.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	public final static int BCRYPT_GENSALT_LOG_ROUNDS = 12;

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

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		if (invalidUser(user)) return null;
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(BCRYPT_GENSALT_LOG_ROUNDS)));
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		if (user == null || user.getId() == null) return null;
		
		User existingUser = findById(user.getId());
		if (existingUser == null) return null;
		
		if (StringUtils.isNotEmpty(user.getPassword()) && !BCrypt.checkpw(user.getPassword(), existingUser.getPassword())) {
			if (invalidUser(user)) return null;
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(BCRYPT_GENSALT_LOG_ROUNDS)));
		} else {
			user.setPassword(existingUser.getPassword());
			if (invalidUser(user)) return null;
		}
		
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public void flush() {
		userRepository.flush();
	}

	@Override
	public Long count() {
		return userRepository.count();
	}
	
	public boolean invalidUser(User user){
		if (user == null || user.getUsername() == null || user.getEmail() == null || user.getRole() == null || user.getPassword() == null) return true;
		if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) return true;
		if (user.getUsername().length() < 4) return true;
		if (user.getPassword().length() < 6) return true;
		if (!user.getEmail().matches(".+@.+[.].{2,}")) return true;
		return false;
	}

}
