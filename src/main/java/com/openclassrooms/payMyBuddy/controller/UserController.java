package com.openclassrooms.payMyBuddy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;
import com.openclassrooms.payMyBuddy.service.UserService;

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
	
	@PostMapping("/creditAccount")
	public String creditAccount (Model model, @RequestParam float amount)
	{
		userService.udpateAccount(userService.findLogUser(), amount);
		model.addAttribute("logUser", userService.findLogUser());
		return "redirect:/";
	}

	@PostMapping("/addContact")
	public String addNewContact(Model model, @RequestParam String email)
	{
		userService.addContact(userService.findLogUser(), email);
		model.addAttribute("logUser", userService.findLogUser());
	
		return "redirect:/";
	}
	
	
	
	@GetMapping("/registration")
	public String showCreateAccountForm(Model model)
	{
		model.addAttribute("newUser", new UserDTO());
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
	// TRY CATCH NE FONCTIONNE PAS 
		@PostMapping("/registration")
		public String registerUserAccount(Model model, @ModelAttribute("newUser") UserDTO userDTO)
		{
			try
			{
				userService.save(userDTO);
			} catch (Exception e)
			{
				
				String error = e.getMessage();
				model.addAttribute("error", error);
			}
			
			return "redirect:/registration?success";
		}
*/
}
