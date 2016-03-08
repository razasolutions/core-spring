package net.raza.core.models;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.elasticsearch.common.joda.time.DateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Abstract Class BaseEntity. Describes attributes that a persistable entity
 * must have in order to suit this application's database patterns.
 *
 * @param <T>
 *            generic type describing the data type of the entity's id.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class AuditedEntity<T> extends BaseEntity<T> {

	/** The complete date of a registry creation. */
	private DateTime createdAt;

	/** The @User responsible of a registry creation. */
	@ManyToOne
	private User createdBy;

	/** The updated at. */
	private DateTime updatedAt;

	/** The updated by. */
	@ManyToOne
	private User updatedBy;

}