package net.raza.core.interceptors;

import java.sql.Timestamp;

import javax.annotation.PostConstruct;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.raza.core.configurations.SpringApplicationContext;
import net.raza.core.models.AuditedEntity;
import net.raza.core.models.User;
import net.raza.core.services.UserService;

@Component
public class AuditInterceptor {

//	static private UserService userService = SpringApplicationContext.getBean(UserService.class);
	
	@PostConstruct
	public void init() {
		System.out.println("AuditInterceptor was invoked.");
	}

	/**
	 * automatic property set before any database persistence
	 * 
	 * @param <T>
	 */
	@PrePersist
	public <T> void auditCreation(AuditedEntity<T> entity) {
		// FIXME: define a way to get the logged user and use it instead of
		// this temporary vague User
//		User temporaryUser = userService.findById(1L);
		entity.setCreatedAt(new Timestamp(new DateTime().getMillis()));
//		entity.setCreatedBy(temporaryUser);
		entity.setUpdatedAt(new Timestamp(new DateTime().getMillis()));
//		entity.setUpdatedBy(temporaryUser);
	}

	@PreUpdate
	public <T> void auditUpdate(AuditedEntity<T> entity) {
		// FIXME: define a way to get the logged user and use it instead of
		// this temporary vague User
//		User temporaryUser = userService.findById(1L);
		entity.setUpdatedAt(new Timestamp(new DateTime().getMillis()));
//		entity.setUpdatedBy(temporaryUser);
	}
}