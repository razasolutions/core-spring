package net.raza.core.models;

import org.joda.time.DateTimeZone;

public interface IBaseAudited {

	DateTimeZone getCreatedAt();
	
	User getCreatedBy();
   
	DateTimeZone getUpdatedAt();
	
	User getUpdatedBy();
	
	Integer getVersion();
	
}
