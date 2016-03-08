package net.raza.core.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import net.raza.core.enums.RoleEnum;

public class RoleAuthorities {

	private static final String PERMISSION_PREFIX = "ROLE_RIGHT_";
	
    public static Collection<? extends GrantedAuthority> getAuthorities(RoleEnum role) {
        Set<CoreAuthority> authorities = new HashSet<CoreAuthority>();
            for (String right : RoleEnum.getRights(role)) {
                CoreAuthority coreAuthority = new CoreAuthority(PERMISSION_PREFIX + right);
                authorities.add(coreAuthority);
            }
        return authorities;
    }
	
}
