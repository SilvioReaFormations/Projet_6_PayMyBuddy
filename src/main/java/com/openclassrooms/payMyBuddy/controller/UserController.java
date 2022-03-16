package com.openclassrooms.payMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;
import com.openclassrooms.payMyBuddy.service.UserService;
import com.openclassrooms.payMyBuddy.service.UserServiceImpl;

@Controller
public class UserController
{
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("newUser") UserDTO userDTO)
	{
		userService.save(userDTO);
		return "redirect:/registration?success";
	}
	
	
	
	@GetMapping("/registration")
	public String showCreateAccountForm(Model model)
	{
		//model.addAttribute("newUser", new UserDTO());
		return "createAccountForm";
	}
	
	
	
	@GetMapping("/login")
	public String showLoginForm()
	{
		return "login";
	}
	
	
	@GetMapping("/")
	public String home(Model model)
	{
		model.addAttribute("logUser", userService.findLogUser());
		return "index";
	}
	
	
	/*
	@PostMapping("/add")
	public String addAccount (int userId, float amount)
	{
		userService.udpateAccount(userId, amount);
		return "index";
	}

	*/


}
