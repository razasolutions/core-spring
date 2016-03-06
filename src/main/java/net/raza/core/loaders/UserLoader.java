package net.raza.core.loaders;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.raza.core.enums.RoleEnum;
import net.raza.core.models.User;
import net.raza.core.services.UserService;

@Component
public class UserLoader {

	@Autowired
	private UserService userService;

	private Logger log = Logger.getLogger(UserLoader.class);

	public void doLoad() {

		if (!userService.findAll().iterator().hasNext()) {

			User superAdmin = new User();
			superAdmin.setLogin("razaadmin");
			superAdmin.setPassword("razapassword");
			superAdmin.setRole(RoleEnum.SUPERADMIN);
			superAdmin.setEmail("superadmin@raza.net");
			
			userService.save(superAdmin);

			log.info("Saved Super Admin - id: " + superAdmin.getId());

		}
	}
}
