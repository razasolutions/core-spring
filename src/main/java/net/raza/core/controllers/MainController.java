package net.raza.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    
	@RequestMapping("/")
    String index(){
        return "public/index";
    }
    
    @RequestMapping("/public/login")
    String login(){
        return "public/login";
    }
    
    @RequestMapping("/public/denied")
    String denied(){
        return "public/denied";
    }
    
}
