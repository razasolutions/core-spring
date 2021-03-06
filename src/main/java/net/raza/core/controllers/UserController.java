package net.raza.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.raza.core.models.User;
import net.raza.core.services.UserService;

@Controller
@SessionAttributes(value = {"managedUser", "operation"} )
public class UserController {

	@Autowired
	private UserService userService;

	private final String PATH = "/admin";

	@RequestMapping(value = PATH + "/user-management", method = RequestMethod.GET)
	public String listUsers(Model model) {
		
		model.addAttribute("users", userService.findAll());
		model.addAttribute("newUser", new User());
		if (model.asMap().get("managedUser") == null) {
			model.addAttribute("managedUser", new User());
		}
		
		return PATH + "/user-management";
		
	}

	@RequestMapping(value = PATH + "/user-management/add", method = RequestMethod.POST)
	public String addUser(User newUser) {
		
		userService.save(newUser);
		// TODO: add a success message into Flash context;
		// TODO: maybe check for authentication here?
		return "redirect:" + PATH + "/user-management";
		
	}

	@RequestMapping(value = PATH + "/user-management/edit", method = RequestMethod.POST)

	public String editUser(@ModelAttribute("managedUser") User managedUser, BindingResult result) {
		userService.update(managedUser);
		// TODO: add a success message into Flash context;
		// TODO: maybe check for authentication here?
		return "redirect:" + PATH + "/user-management";
	}

	@RequestMapping(value = PATH + "/user-management/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Long id) {
		userService.delete(id);
		// TODO: add a success message into Flash context;
		// TODO: maybe check for authentication here?
		return "redirect:" + PATH + "/user-management";
	}

	@RequestMapping(value = PATH + "/user-management/manage/{id}", method = RequestMethod.GET)
	public String manageUser(@PathVariable Long id,
			@RequestParam(value = "operation", required = true) String operation, Model model) {
		
		model.addAttribute("managedUser", userService.findById(id));
		if (operation.equals("delete")) {
			return "redirect:" + PATH + "/user-management#deleteDialog";
		}
		model.addAttribute("operation", operation);
		// TODO: handle modal Dialog opening with a Flash attribute instead of
		// with #userDialog
		// it's better to handle modal dialog opening via Flash (because of page
		// updating (F5) and back/forward issues).
		return "redirect:" + PATH + "/user-management#userDialog";
	}

}
