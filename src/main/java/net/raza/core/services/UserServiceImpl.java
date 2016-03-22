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
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(BCRYPT_GENSALT_LOG_ROUNDS)));
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		User existingUser = findById(user.getId());
		
		if (StringUtils.isNotEmpty(user.getPassword()) && !BCrypt.checkpw(user.getPassword(), existingUser.getPassword())) {
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(BCRYPT_GENSALT_LOG_ROUNDS)));
		} else {
			user.setPassword(existingUser.getPassword());
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

}
