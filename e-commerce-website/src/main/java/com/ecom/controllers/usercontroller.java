package com.ecom.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Locale.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.model.UserDtls;
import com.ecom.model.category;
import com.ecom.services.categoryservice;
import com.ecom.services.userservice;

@Controller
@RequestMapping("/user")
public class usercontroller {
	@Autowired
	private userservice userService;
	@Autowired
	private categoryservice categoryService;
    @GetMapping("/")
    public String home(){
        return "user/home";
    }
    @ModelAttribute
	public void getUserDetails(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			UserDtls userDtls = userService.getUserByEmail(email);
			m.addAttribute("user", userDtls);
		}

		List<category> allActiveCategory = categoryService.getAllActiveCategory();
		m.addAttribute("categorys", allActiveCategory);
	}
}
