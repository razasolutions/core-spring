package net.raza.core.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityLoader {

	@Autowired
	private UserLoader userLoader;
	@Autowired
	private ProductLoader productLoader;

	public void load() {

		userLoader.load();
		productLoader.load();
		
	}
}
