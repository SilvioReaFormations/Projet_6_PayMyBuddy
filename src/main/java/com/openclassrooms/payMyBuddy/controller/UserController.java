package com.openclassrooms.payMyBuddy.controller;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.payMyBuddy.exception.ContactException;
import com.openclassrooms.payMyBuddy.exception.TransactionException;
import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;
import com.openclassrooms.payMyBuddy.service.UserService;

/**
 * UserController Class
 * @author Silvio
 *
 */

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
			userService.saveNewUser(userDTO);
		} 
		
		catch (SQLIntegrityConstraintViolationException e)
		{
			String exception = e.getMessage();
			model.addAttribute("exception1", exception);
		}
	
		return "redirect:/registration?success";
	}
	
	
	
	@PostMapping("/creditAccount")
	public String creditAccount (Model model, @RequestParam double amount,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size)
	{
		
		model.addAttribute("logUser", userService.findLogUser());
		Page<Operation> pageOperation = userService.findOperationByUser(page, size);
		model.addAttribute("list", pageOperation.getContent() );
		model.addAttribute("pages", new int[pageOperation.getTotalPages()]);
		model.addAttribute("currentPage", page);
		
		try
		{
			userService.udpateAccount(userService.findLogUser(), amount);
		} 
		catch (TransactionException e)
		{
			String exception = e.getMessage();
			model.addAttribute("exception1", exception);
			return "index";
		}
	
		return "redirect:/?creditSuccess";
	}
	
	

	@PostMapping("/addContact")
	public String addNewContact(Model model, @RequestParam String email,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size)
	{
		
		model.addAttribute("logUser", userService.findLogUser());
		Page<Operation> pageOperation = userService.findOperationByUser(page, size);
		model.addAttribute("list", pageOperation.getContent() );
		model.addAttribute("pages", new int[pageOperation.getTotalPages()]);
		model.addAttribute("currentPage", page);
		
		try
		{
			userService.addContact(userService.findLogUser(), email);
		} 
		catch (ContactException e)
		{
			String exception = e.getMessage();
			model.addAttribute("exception1", exception);
			return "index";
		}
		
		return "redirect:/?contactSuccess";
	}
	
	

}
