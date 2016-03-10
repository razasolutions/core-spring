package net.raza.core.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.raza.core.models.User;
import net.raza.core.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
    
    @RequestMapping(value = "/admin/userManagement", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("users", userService.findAll());
        model.addAttribute("newUser", new User());
        return "admin/userManagement";
    }
    
    @RequestMapping(value = "/admin/newUser", method = RequestMethod.POST)
    public String newUser(User newUser){
    	userService.save(newUser);
    	return "redirect:/admin/userManagement";
    }
}
