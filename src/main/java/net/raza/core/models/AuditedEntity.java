package net.raza.core.models;

import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditedEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 4013874760995046753L;

	/** The complete date of a registry creation. */
	@CreatedDate
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime createdAt;

	/** The @User responsible of a registry creation. */
	@ManyToOne
	@CreatedBy
	private User createdBy;

	/** The updated at. */
	@LastModifiedDate
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime updatedAt;

	/** The updated by. */
	@ManyToOne
	@LastModifiedBy
	private User updatedBy;

}