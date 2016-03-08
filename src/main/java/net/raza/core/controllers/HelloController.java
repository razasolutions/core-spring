package net.raza.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/restricted/hello")
    String redirect(){
        return "restricted/hello";
    }
}
