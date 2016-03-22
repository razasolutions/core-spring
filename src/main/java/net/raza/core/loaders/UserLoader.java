package net.raza.core.loaders;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.raza.core.enums.RoleEnum;
import net.raza.core.models.User;
import net.raza.core.security.RoleAuthorities;
import net.raza.core.services.UserService;

@Component
public class UserLoader {

	@Autowired
	private UserService userService;

	private Logger log = Logger.getLogger(UserLoader.class);

	public void load() {

		if (userService.count() == 0) {

			RoleEnum role = RoleEnum.SUPERADMIN;
			User superAdmin = new User();
			superAdmin.setUsername("razaadmin");
			superAdmin.setPassword("$2a$10$RwKfvN.2BFuKsjMy4s7EiOElBPrTVExHBPfe3VOau8P.shRcSL8gK");
			superAdmin.setRole(role);
			superAdmin.setEmail("superadmin@raza.net");
			
			userService.save(superAdmin);
			userService.flush();

			log.info("Saved Super Admin - id: " + superAdmin.getId());

		}
	}
}
