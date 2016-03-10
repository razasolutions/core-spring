package net.raza.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
		if (user == null){
			//TODO we want exception here?
			throw new UsernameNotFoundException(String.format("Invalid credentials", authentication.getPrincipal()));
		}
		
		// TODO encrypt
		String pw = authentication.getCredentials().toString();
		if (!user.getPassword().equals(pw)){
			// TODO we want exception here?
			throw new BadCredentialsException("Invalid credentials");
		}
		
		return new UsernamePasswordAuthenticationToken(user, null, RoleAuthorities.getAuthorities(user.getRole()));
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}
