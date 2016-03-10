package net.raza.core.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import net.raza.core.security.SecurityAuthenticationProvider;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityAuthenticationProvider sap;
	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        	.authorizeRequests()
        		.antMatchers("/webjars/**", "/", "/home", "/public/**").permitAll()
        		.antMatchers("/restricted/**").access("isAuthenticated()")
        		.antMatchers("/admin/**").hasAnyAuthority("ROLE_RIGHT_CREATE")
            	.anyRequest().authenticated()
            	.and()
        	.formLogin()
            	.loginPage("/public/login")
            	.permitAll()
            	.and()
        	.logout()
            	.permitAll();
 
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(sap);
    }
}

