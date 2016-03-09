package net.raza.core.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;

/**
 * The Abstract Class BaseEntity. Describes attributes that a persistable entity must have in order to suit this application's database patterns.
 *
 * @param <T> generic type describing the data type of the entity's id.
 */
@Data
@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {

    /**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/** The entity id, used to bind database relations */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private T id;
    
    /** The version.  */
    @Version
    private Integer version;

}
