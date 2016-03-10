package net.raza.core.security;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 8022694174468422445L;
	
	@Getter
	private String authority;
	
	public Authority(String authority){
		this.authority = authority;
	}

	@Override
    public int hashCode() {
        return authority.hashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Authority)) return false;
        return ((Authority) obj).getAuthority().equals(authority);
    }
}
