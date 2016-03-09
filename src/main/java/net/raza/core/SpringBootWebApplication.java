package net.raza.core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import net.raza.core.loaders.EntityLoader;

@SpringBootApplication
public class SpringBootWebApplication  extends SpringBootServletInitializer {
	
	private static Class<SpringBootWebApplication> applicationClass = SpringBootWebApplication.class;

	@Autowired
	private EntityLoader entityLoader;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
    
    /**
     * Used for handling initial data load into a database whenever the application is deployed.
     */
    @PostConstruct
    public void doEntityLoad() { 
        entityLoader.doLoad();
    }
    
}
