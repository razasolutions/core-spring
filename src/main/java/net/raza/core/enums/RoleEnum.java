package net.raza.core.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/**
 * The Enum RoleEnum.
 * 
 * Handles a @User privilege set.
 * 
 * @author tazabreu
 * 
 */
public enum RoleEnum {

	SUPERADMIN("ROLE_SUPER"), 
	ADMIN("ROLE_ADMIN"), 
	USER("ROLE_USER");
	
	RoleEnum(String role) {
		this.role = role;
	}
	
	@Getter
	private final String role;

	public static List<String> getRights(RoleEnum roleEnum){
		switch (roleEnum.getRole()) {
		case "ROLE_SUPER":
			return Arrays.asList("SUPER");
		case "ROLE_ADMIN":
			return Arrays.asList("ADMIN");
		case "ROLE_USER":
			return Arrays.asList("USER");
		default:
			return null;
		}
	}
}
