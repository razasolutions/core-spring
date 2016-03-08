package net.raza.core.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.raza.core.enums.RoleEnum;

/**
 * 
 * User entity. Relative to every person allowed to use and/or manage the system
 * 
 * @author tazabreu
 *
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity<Long> {

    /** The user login */
    private String userName;
    
    /** The email. */
    private String email;
    
    /** The password. */
    private String password;
    
    /** The role. */
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
