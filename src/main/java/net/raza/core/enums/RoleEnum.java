package net.raza.core.enums;

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

	SUPERADMIN("enums.role.superadmin"), ADMIN("enums.role.admin"), USER("enums.role.user");

	
	RoleEnum(String messageCode) {
		this.messageCode = messageCode;
	}
	
	/**
	 * Gets the message code.
	 *
	 * @return the message code
	 */
	@Getter
	private final String messageCode;

}
