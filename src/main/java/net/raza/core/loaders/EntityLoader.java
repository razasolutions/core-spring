package net.raza.core.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityLoader {

	@Autowired
	private UserLoader userLoader;

	public void load() {

		userLoader.load();
		
	}
}

