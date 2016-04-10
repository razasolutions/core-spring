package net.raza.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
    
	@RequestMapping("/restricted/dashboard")
    String index(){
        return "/restricted/dashboard";
    }

}
