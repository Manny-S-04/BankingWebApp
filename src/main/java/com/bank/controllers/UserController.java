package com.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.entities.User;
import com.bank.services.UserRegistrationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserRegistrationService registrationService;

	@GetMapping("/greeting")
	public String greeting() {

		return "greeting";
	}

	@GetMapping("/register")
	public String showRegisterform() {

		return "register";

	}

	@PostMapping("/register")
	public String register(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName,
			@RequestParam("email") String email, @RequestParam("password") String password) {

		User userExists = registrationService.findEmail(email);

		if (userExists == null) {

			User registeredUser = registrationService.save(new User(firstName, lastName, email, password));

			if (registeredUser != null) {
				return "login";
			} else {
				return "error";
			}
		}else {
			return "registerfail";
		}
		
		
		
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, Model model) {
		// Call the login method from UserRepository to authenticate the user
		User authenticatedUser = registrationService.login(email, password);

		if (authenticatedUser != null) {
			// User is authenticated, add the user to the session
			session.setAttribute("loggedinuser", authenticatedUser);
			session.setAttribute("firstname", authenticatedUser.getFirstname());
			session.setAttribute("lastname", authenticatedUser.getLastname());
			session.setAttribute("email", authenticatedUser.getEmail());
			session.setAttribute("password", authenticatedUser.getPassword());

			// Redirect to the user details page
			return "redirect:/userdetails";
		} else {
			// Authentication failed, redirect back to the login page with an error message
			return "greeting";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// Invalidate the session to log out the user
		session.invalidate();

		// Redirect to the login page or any other desired page
		return "redirect:/login";
	}

	@GetMapping("/userdetails")
	public String userDetails(HttpSession session) {

		return "userdetails";

	}

}
