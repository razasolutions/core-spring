package net.raza.core.models;

import java.sql.Timestamp;

import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.raza.core.interceptors.AuditInterceptor;

/**
 * The Abstract Class BaseEntity. Describes attributes that a persistable entity
 * must have in order to suit this application's database patterns.
 *
 * @param <T>
 *            generic type describing the data type of the entity's id.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(value = { AuditInterceptor.class })
@MappedSuperclass
public abstract class AuditedEntity<T> extends BaseEntity<T> {

	/** The complete date of a registry creation. */
	private Timestamp createdAt;

	/** The @User responsible of a registry creation. */
	@ManyToOne
	private User createdBy;

	/** The updated at. */
	private Timestamp updatedAt;

	/** The updated by. */
	@ManyToOne
	private User updatedBy;

}