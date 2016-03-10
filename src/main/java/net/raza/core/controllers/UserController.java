package net.raza.core.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("editUser", new User());
        return "admin/userManagement";
    }
    
    @RequestMapping(value = "/admin/newUser", method = RequestMethod.POST)
    public String newUser(User newUser){
    	userService.save(newUser);
    	return "redirect:/admin/userManagement";
    }
    
    @RequestMapping("/admin/user/{id}")
    public String showUser(@PathVariable Long id, Model model){
    	model.addAttribute("editUser", userService.findById(id));
        return "admin/userManagement2";
    }
    
    
    @RequestMapping(value = "/admin/editUser", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("editUser") User editUser){
    	userService.save(editUser);
    	return "redirect:/admin/userManagement";
    }
}

