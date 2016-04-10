package net.raza.core.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.raza.core.security.SecurityAuthenticationProvider;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityAuthenticationProvider sap;
	
	@Autowired
	private AuthenticationSuccessHandler ash;
	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        	.authorizeRequests()
        		.antMatchers("/", "/public/**").permitAll()
        		.antMatchers("/restricted/*").hasAnyAuthority("ROLE_RIGHT_SUPER", "ROLE_RIGHT_ADMIN", "ROLE_RIGHT_USER")
            	.anyRequest().authenticated()
        		.antMatchers("/restricted/user/**").hasAnyAuthority("ROLE_RIGHT_SUPER", "ROLE_RIGHT_USER")
            	.anyRequest().authenticated()
        		.antMatchers("/restricted/admin/**").hasAnyAuthority("ROLE_RIGHT_SUPER", "ROLE_RIGHT_ADMIN")
            	.anyRequest().authenticated()
            	.and()
        	.formLogin()
            	.loginPage("/public/login")
            	.permitAll()
            	.successHandler(ash)
            	.and()
        	.logout()
        		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        		.logoutSuccessUrl("/");
 
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(sap);
    }
}

