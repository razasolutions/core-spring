package net.raza.core.models;

/**
 * The Interface IBaseEntity. Describes methods that a persistable entity must have in order to suit this application's database patterns.
 *
 * @param <T> generic type describing the data type of the entity's id.
 */
public interface IBaseEntity<T> {

	T getId();
	
	Integer getVersion();
   
}
