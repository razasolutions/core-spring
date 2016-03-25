package net.raza.core.configurations;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.raza.core.models.User;

@ControllerAdvice
public class ControllerConfiguration {

	/**
	 * 
	 * Method that adds the attribute "principal" to every Controller,
	 * presenting info about an eventual logged-in user.
	 * 
	 * @param principal
	 * @return the current logged-in username
	 */
	@ModelAttribute("username")
	public String addUsername(UsernamePasswordAuthenticationToken authentication) {
		return authentication == null ? null : ((User) authentication.getPrincipal()).getUsername();
	}

}