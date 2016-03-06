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
	
	@Autowired
	private EntityLoader entityLoader;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
    
    @PostConstruct
    public void doEntityLoad() { 
        entityLoader.doLoad();
    }

    private static Class<SpringBootWebApplication> applicationClass = SpringBootWebApplication.class;
    
}
