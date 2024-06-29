package com.ecom.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.model.UserDtls;
import com.ecom.model.Category;
import com.ecom.services.CategoryService;
import com.ecom.services.UserService;

@Controller
@RequestMapping("/profile")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
//    @GetMapping("/")
//    public String home(){
//        return "user/home";
//    }
    @ModelAttribute
	public void getUserDetails(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			UserDtls user = userService.getUserByEmail(email);
			m.addAttribute("user", user);
		}

		List<Category> allActiveCategory = categoryService.getAllActiveCategory();
		m.addAttribute("categorys", allActiveCategory);
	}


		@GetMapping("/users")
		public String getUserDetails(Model model) {
			UserDtls user = new UserDtls();
			model.addAttribute("users", user);
			return "profile/users";  // This should match the name of your JSP file without the .jsp extension
		}
	}

