package com.openclassrooms.payMyBuddy.controller;


import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.payMyBuddy.exception.ContactException;
import com.openclassrooms.payMyBuddy.exception.TransactionException;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;
import com.openclassrooms.payMyBuddy.service.UserService;

@Controller
public class UserController
{
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/registration")
	public String registerUserAccount(Model model, @ModelAttribute("newUser") UserDTO userDTO)
	{
		
		try
		{
			userService.save(userDTO);
		} 
		
		catch (SQLIntegrityConstraintViolationException e)
		{
			String exception = e.getMessage();
			model.addAttribute("exception1", exception);
		}
	
		return "redirect:/registration?success";
	}
	
	
	@PostMapping("/creditAccount")
	public String creditAccount (Model model, @RequestParam float amount)
	{
		try
		{
			userService.udpateAccount(userService.findLogUser(), amount);
		} 
		catch (TransactionException e)
		{
			String exception = e.getMessage();
			model.addAttribute("exception1", exception);
		}
		model.addAttribute("logUser", userService.findLogUser());
		return "index";
	}

	@PostMapping("/addContact")
	public String addNewContact(Model model, @RequestParam String email)
	{
		try
		{
			userService.addContact(userService.findLogUser(), email);
		} 
		catch (ContactException e)
		{
			String exception = e.getMessage();
			model.addAttribute("exception1", exception);
		}
		model.addAttribute("logUser", userService.findLogUser());
		return "index";
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
	

}
