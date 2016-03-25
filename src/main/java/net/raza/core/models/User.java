package net.raza.core.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.raza.core.enums.RoleEnum;

/**
 * 
 * User entity. Relative to every person allowed to use and/or manage the system
 * 
 * @author tazabreu
 *
 */

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(of="username", callSuper = false)
public class User extends BaseEntity<Long> {

	private static final long serialVersionUID = 871861820518861281L;
	
	public static final Long SYSTEM_ID = 1L;

	/** The user login */
	@NotNull
	@Column(unique = true)
    private String username;
    
    /** The email. */
	@Email
	@Column(unique = true)
    private String email;
    
    /** The password. */
	@NotNull
	@Size(min = 6)
    private String password;
    
    /** The role. */
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    
    /** Avatar image */
    @Lob
    private byte[] avatar;

}
