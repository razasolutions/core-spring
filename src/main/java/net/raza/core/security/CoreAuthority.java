package net.raza.core.security;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@SuppressWarnings("serial")
public class CoreAuthority implements GrantedAuthority {

	@Getter
	private String authority;
	
	public CoreAuthority(String authority){
		this.authority = authority;
	}

	@Override
    public int hashCode() {
        return authority.hashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof CoreAuthority)) return false;
        return ((CoreAuthority) obj).getAuthority().equals(authority);
    }
}
