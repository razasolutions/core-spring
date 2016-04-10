package net.raza.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import net.raza.core.enums.RoleEnum;
import net.raza.core.models.User;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {  
  
    @Override  
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,  
                                        HttpServletResponse httpServletResponse,  
                                        Authentication authentication)  
            throws IOException, ServletException {  
        //do some logic here if you want something to be done whenever  
        //the user successfully logs in.  
  
        HttpSession session = httpServletRequest.getSession();  
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        session.setAttribute("username", authenticatedUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());  
  
        //set our response to OK status  
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);  
  
        //since we have created our custom success handler, its up to us to where  
        //we will redirect the user after successfully login
        
        if (authenticatedUser.getRole().equals(RoleEnum.USER)) {
        	httpServletResponse.sendRedirect("../restricted/dashboard");  
        } else {
        	// TODO maybe should send different-roled user elsewhere?
        	httpServletResponse.sendRedirect("../restricted/dashboard");  
        }
    }  
}  