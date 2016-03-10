package net.raza.core.enums;

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

	SUPERADMIN("ROLE_SUPER", "role.super"), 
	ADMIN("ROLE_ADMIN", "role.admin"), 
	USER("ROLE_USER", "role.user");
	
	RoleEnum(String role, String key) {
		this.role = role;
		this.key = key;
	}
	
	@Getter
	private final String role;
	
	@Getter
	private final String key;

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
