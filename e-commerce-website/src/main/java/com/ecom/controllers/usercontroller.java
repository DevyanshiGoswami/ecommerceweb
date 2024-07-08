package com.ecom.controllers;

import java.security.Principal;

import com.ecom.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.model.UserDtls;
import com.ecom.services.CategoryService;
import com.ecom.services.UserService;
import com.ecom.model.Cart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	@Autowired
	private CategoryService categoryService;
    @GetMapping()
    public String home(Principal p, Model m){
		if (p != null) {
			String email = p.getName();
			UserDtls user = userService.getUserByEmail(email);
			m.addAttribute("UserDtls", user);
		}
		return "profile/users";
    }

	@GetMapping("/addCart")
	public String addToCart(@RequestParam Integer pid, @RequestParam Integer uid, HttpSession session) {
		Cart saveCart = cartService.saveCart(pid, uid);

		if (ObjectUtils.isEmpty(saveCart)) {
			session.setAttribute("errorMsg", "Product add to cart failed");
		}else {
			session.setAttribute("succMsg", "Product added to cart");
		}
		return "redirect:/product/" + pid;
	}

//    @ModelAttribute
//	public void getUserDetails(Principal p, Model m) {
//		if (p != null) {
//			String email = p.getName();
//			UserDtls user = userService.getUserByEmail(email);
//			m.addAttribute("user", user);
//		}
//
//		List<Category> allActiveCategory = categoryService.getAllActiveCategory();
//		m.addAttribute("categorys", allActiveCategory);
//	}


//		@GetMapping("/users")
//		public String getUserDetails(Model model) {
//			UserDtls user = new UserDtls();
//			model.addAttribute("users", user);
//			return "profile/users";  // This should match the name of your JSP file without the .jsp extension
//		}
	}

