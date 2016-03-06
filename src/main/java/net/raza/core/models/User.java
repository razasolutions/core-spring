package net.raza.core.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.DateTimeZone;
import org.springframework.data.annotation.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * User entity. Relative to every person allowed to use and/or manage the system
 * 
 * @author Taz
 *
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements IBaseEntity<Long>, IBaseAudited {

	
	/// Interface related attributes //
	
    /** The entity id, used to bind database relations */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Version
    private Integer version;
    
    private DateTimeZone createdAt;
    
    @ManyToOne
    private User createdBy;
    
    private DateTimeZone updatedAt;
    
    @ManyToOne
    private User updatedBy;
    
    
	/// Specific attributes //
    
    /** The product id, used to uniquely identify a product in logical context */
    private String login;
    
    private String email;
    
    private String password;

}
