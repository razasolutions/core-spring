package net.raza.core.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import net.raza.core.models.User;
import net.raza.core.services.UserService;

@Component
public class AuditorAwareImpl implements AuditorAware<User> {
	
	@Autowired
	UserService userService;
  
    @Override
    public User getCurrentAuditor() {
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
          return userService.findById(User.SYSTEM_ID);
        } else {
        	return (User) authentication.getPrincipal();
        }
        
    }
 
}