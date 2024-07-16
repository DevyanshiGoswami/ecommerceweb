package com.ecom.controllers;

import com.ecom.model.UserDtls;
import com.ecom.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private UserService userService;
    @GetMapping("/forgot-password")
    public String showForgotPassword(HttpSession session, Model m) {
        if (session.getAttribute("userValid") != null && (Boolean) session.getAttribute("userValid")) {
            UserDtls user = (UserDtls) session.getAttribute("validUserDetail");
            m.addAttribute("user", user);
        }
        return "forgot_password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, HttpSession session, Model m) {
        UserDtls userByEmail = userService.getUserByEmail(email);

        if (userByEmail == null) {
            session.setAttribute("errorMsg", "Invalid email");
            session.removeAttribute("succMsg");
            session.removeAttribute("userValid");
        } else {
            session.setAttribute("succMsg", "Email sent successfully");
            session.setAttribute("userValid", true);
            session.setAttribute("validUserDetail", userByEmail);
            session.removeAttribute("errorMsg");
        }

        return "redirect:/forgot-password";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam("userId") String email, @RequestParam("password") String password, HttpSession session) throws UnsupportedEncodingException {
        if (email == null || password == null || password.trim().isEmpty()) {
            System.out.println("Invalid userId or password");
            return "redirect:/reset-password?error";
        }

        UserDtls user = userService.getUserByEmail(email);
        if (user == null) {
            System.out.println("User not found for userId: " + email);
            return "redirect:/reset-password?error";
        }

        user.setPassword(password);
        userService.saveUser(user);
        session.invalidate();  // Clear session after successful reset

        return "redirect:/login";
    }
}
