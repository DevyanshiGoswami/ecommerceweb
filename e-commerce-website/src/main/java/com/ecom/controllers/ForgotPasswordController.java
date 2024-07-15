package com.ecom.controllers;

import com.ecom.model.UserDtls;
import com.ecom.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private UserService userService;
    @GetMapping("/forgot-password")
    public String showForgotPassword() {
        return "forgot_password";
    }
//    @PostMapping("/forgot-password")
//    public String processForgotPassword(@RequestParam("email") String email) {
//        UserDtls user = userService.getUserByEmail(email);
//        if (user == null) {
//            // Handle case where email is not found
//            return "redirect:/forgot-password?error";
//        }
//
//        // Redirect to the reset password form with user ID as a parameter
////        return "redirect:/reset-password?userEmail=" + user.getEmail();
//
////        return "redirect:/reset-password";
//        return "redirect:/reset-password?userEmail=" + user.getEmail();
//    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email , Model m) throws UnsupportedEncodingException {
        UserDtls user = userService.getUserByEmail(email);

        if (user == null) {
            // Handle case where email is not found
            return  "/forgot-password?error";
        }
        m.addAttribute("userId",user.getId());
        // Redirect to the reset password form with user ID as a parameter
        return "redirect:/reset-password?userId=" + user.getId();
    }
//    @GetMapping("/reset-password")
//    public String showResetPasswordForm(@RequestParam("userEmail") String email, Model model) {
//    public String showResetPasswordForm() {
//        UserDtls user = userService.getUserByEmail(email);
//        if (user == null) {
//            // Handle case where user is not found
//            return "reset-password?error";
//        }
//        model.addAttribute("userEmail", email);
        // Display reset password form
//        return "reset_password";
//    }
@GetMapping("/reset-password")
public String showResetPasswordForm() {
//    System.out.println("Received userId: " + userId);
    // Pass the email to the Thymeleaf template
//    m.addAttribute("userId", userId);
    return "reset_password";
}
    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam("userId") Long userId, @RequestParam("password") String password) throws UnsupportedEncodingException {
        if (userId == null || password == null || password.trim().isEmpty()) {
            System.out.println("Invalid userId or password");
            // Handle case where userId or password is missing or invalid
            return "redirect:/reset-password?error";
        }
        UserDtls user = userService.findById(userId);
        if (user == null) {
            System.out.println("User not found for userId: " + userId);
            // Handle case where user is not found
            return "redirect:/reset-password?error";
        }
        user.setPassword(password);
        userService.updateUser(user);

        return "redirect:/login";
    }
}
