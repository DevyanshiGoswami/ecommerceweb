package com.ecom.controllers;

import java.security.Principal;
import java.util.List;

import com.ecom.model.*;
import com.ecom.services.CartService;
import com.ecom.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.ecom.services.CategoryService;
import com.ecom.services.UserService;

@Controller
@RequestMapping("/profile")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
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
	@ModelAttribute
	public void getUserDetails(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			UserDtls userDtls = userService.getUserByEmail(email);
			m.addAttribute("user", userDtls);
			Integer countCart = cartService.getCountCart(userDtls.getId());
			m.addAttribute("countCart", countCart);
		}

		List<Category> allActiveCategory = categoryService.getAllActiveCategory();
		m.addAttribute("categorys", allActiveCategory);
	}

	@GetMapping("/addCart")
	public String addToCart(@RequestParam Integer pid, @RequestParam Integer uid, HttpSession session) {
		Cart saveCart = cartService.saveCart(pid, uid);

		if (ObjectUtils.isEmpty(saveCart)) {
			session.setAttribute("errorMsg", "Product add to cart failed");
		} else {
			session.setAttribute("succMsg", "Product added to cart");
		}
		return "redirect:/product/" + pid;
	}

	@GetMapping("/cart")
	public String loadCartPage(Principal p, Model m) {

		UserDtls user = getLoggedInUserDetails(p);
		List<Cart> carts = cartService.getCartsByUser(user.getId());
		m.addAttribute("carts", carts);
		if (carts.size() > 0) {
			Double totalOrderPrice = carts.get(carts.size() - 1).getTotalOrderPrice();
			m.addAttribute("totalOrderPrice", totalOrderPrice);
		}
		return "/profile/cart";
	}

	@GetMapping("/cartQuantityUpdate")
	public String updateCartQuantity(@RequestParam String sy, @RequestParam Integer cid) {
		cartService.updateQuantity(sy, cid);
		return "redirect:/profile/cart";
	}

	private UserDtls getLoggedInUserDetails(Principal p) {
		String email = p.getName();
		UserDtls userDtls = userService.getUserByEmail(email);
		return userDtls;
	}


//	@GetMapping("/orders")
//	public String orderPage(String status,Model m) {
//
//		List<ProductOrder> orders = orderService.getOrdersByStatus(status);
////		List<ProductOrder> orders=orderService.getAllOrders();
//		// Add a logging statement to check if orders are being retrieved correctly
//		System.out.println("Orders retrieved: " + orders.size());
//
//		// Add the orders list to the model
//		m.addAttribute("orders", orders);
//
//		return "profile/order";
//	}

	@GetMapping("/orders")
	public String orderPage(Principal p, Model m) {
		UserDtls user = getLoggedInUserDetails(p);
		List<Cart> carts = cartService.getCartsByUser(user.getId());
		m.addAttribute("carts", carts);
		if (carts.size() > 0) {
			Double orderPrice = carts.get(carts.size() - 1).getTotalOrderPrice();
			Double totalOrderPrice = carts.get(carts.size() - 1).getTotalOrderPrice() + 250 + 100;
			m.addAttribute("orderPrice", orderPrice);
			m.addAttribute("totalOrderPrice", totalOrderPrice);
		}
		return "profile/order";
	}
	@GetMapping("/order/{id}")
	public ResponseEntity<ProductOrder> getOrderById(@PathVariable String id,Model m) {
		ProductOrder order=orderService.getOrderById(id);
		m.addAttribute("orders",order);
		return ResponseEntity.ok().body(orderService.getOrderById(id));

	};
	@GetMapping("/success")
	public String getOrderStatus(String status,Model m) {
		List<ProductOrder> orders = orderService.getOrdersByStatus(status);
//		ProductOrder orders=orderService.getOrderById(id);
		m.addAttribute("orders", orders);
		return "profile/success"; // return the Thymeleaf template
	}
//	@GetMapping("/success")
//	public String getorderstatus(Principal p,Model m){
//		if(p!=null){
////			String status=p.getName();
//			List<ProductOrder> order=orderService.getAllOrders();
//			m.addAttribute("orders",order);
//		}
//		return "profile/success";
//	}

	@PostMapping("/save-order")
	public String saveOrder(@ModelAttribute OrderRequest request, Principal p) {
		// System.out.println(request);
		UserDtls user = getLoggedInUserDetails(p);
		orderService.saveOrder(user.getId(), request);

		return "redirect:/profile/success";
	}


	@GetMapping("/my_orders")
	public String myOrder(Model m, Principal p) {
		UserDtls loginUser = getLoggedInUserDetails(p);
		Integer userId =loginUser.getId();
		List<ProductOrder> orders = orderService.getOrderByUserId(userId);
		m.addAttribute("orders", orders);
		return "profile/my_orders";
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

