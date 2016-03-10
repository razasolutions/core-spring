package net.raza.core.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import net.raza.core.enums.RoleEnum;

public class RoleAuthorities {

	private static final String PERMISSION_PREFIX = "ROLE_RIGHT_";
	
    public static Set<Authority> getAuthorities(RoleEnum role) {
        Set<Authority> authorities = new HashSet<Authority>();
            for (String authority : RoleEnum.getAuthorities(role)) {
                authorities.add(new Authority(PERMISSION_PREFIX + authority));
            }
        return authorities;
    }
	
}
