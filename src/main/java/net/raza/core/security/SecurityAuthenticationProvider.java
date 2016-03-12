package net.raza.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import net.raza.core.models.User;
import net.raza.core.services.UserService;

@Component(value = "authenticationProvider")
public class SecurityAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		User user = userService.findByUsername(authentication.getPrincipal().toString());
		String pw = authentication.getCredentials().toString();
		
		if (user == null || pw == null || !BCrypt.checkpw(pw, user.getPassword())){
			return null;
		}
		
		return new UsernamePasswordAuthenticationToken(user, null, RoleAuthorities.getAuthorities(user.getRole()));
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}
