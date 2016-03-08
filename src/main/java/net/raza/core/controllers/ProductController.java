package net.raza.core.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.raza.core.models.Product;
import net.raza.core.services.ProductService;
import net.raza.core.services.UserService;

@Controller
public class ProductController {

	@Autowired
    private ProductService productService;

	@Autowired
	private UserService userService;
    

    @RequestMapping(value = "/public/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.listAllProducts());
        
        userService.findAll();
        
        System.out.println("Returning Products:");
        return "public/products";
    }

    @RequestMapping("/restricted/product/{id}")
    public String showProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "restricted/productshow";
    }

    @RequestMapping("/restricted/product/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "restricted/productform";
    }

    @RequestMapping("/restricted/product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "restricted/productform";
    }

    @RequestMapping(value = "/restricted/product", method = RequestMethod.POST)
    public String saveProduct(Product product){

        productService.saveProduct(product);

        return "redirect:/restricted/product/" + product.getId();
    }

}
