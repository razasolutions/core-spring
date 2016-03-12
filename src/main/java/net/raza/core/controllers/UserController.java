package net.raza.core.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.raza.core.models.User;
import net.raza.core.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	private final String PATH = "/admin";
    
    @RequestMapping(value = PATH + "/userManagement", method = RequestMethod.GET)
    public String listUsers(Model model){
        model.addAttribute("users", userService.findAll());
        model.addAttribute("newUser", new User());
        if (model.asMap().get("managedUser") != null) {
        	model.addAttribute("managedUser", model.asMap().get("managedUser"));
        } else {
        	model.addAttribute("managedUser", new User());
        }
        return PATH + "/userManagement";
    }
    
    @RequestMapping(value = PATH + "/userManagement/add", method = RequestMethod.POST)
    public String addUser(User newUser){
    	userService.save(newUser);
    	// TODO: add a success message into Flash context;
    	// TODO: maybe check for authentication here?
    	return "redirect:" + PATH + "/userManagement";
    }
    
    @RequestMapping(value = PATH + "/userManagement/edit", method = RequestMethod.POST)
    public String editUser(User managedUser){
    	userService.save(managedUser);
    	// TODO: add a success message into Flash context;
    	// TODO: maybe check for authentication here?
    	return "redirect:" + PATH + "/userManagement";
    }
    
    @RequestMapping(value = PATH + "/userManagement/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id){
    	userService.delete(id);
    	// TODO: add a success message into Flash context;
    	// TODO: maybe check for authentication here?
    	return "redirect:" + PATH + "/userManagement";
    }
    
    @RequestMapping(value = PATH + "/userManagement/manage/{id}", method = RequestMethod.GET)
    public String manageUser(@PathVariable Long id, @RequestParam(value = "operation", required = true) String operation, RedirectAttributes redirectAttributes){
    	redirectAttributes.addFlashAttribute("managedUser", userService.findById(id));
    	redirectAttributes.addFlashAttribute("operation", operation);
    	// TODO: handle modal Dialog opening with a Flash attribute instead of with #userDialog
    	// it's better to handle modal dialog opening via Flash (because of page updating (F5) and back/forward issues).
    	return "redirect:" + PATH + "/userManagement#userDialog";
    }


}

