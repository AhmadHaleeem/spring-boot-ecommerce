package com.haleem.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haleem.ecommerce.models.User;
import com.haleem.ecommerce.repositories.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String register(User user) {
		return "register";
	}

	@PostMapping
	public String register(@Valid User user, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "register";
		}

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			model.addAttribute("passwordMatchProblem", "Passwords do not match!");
			return "register";
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);

		return "redirect:/login";
	}

}
