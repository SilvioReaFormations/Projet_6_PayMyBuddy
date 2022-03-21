package com.openclassrooms.payMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.dto.OperationDTO;
import com.openclassrooms.payMyBuddy.service.OperationService;
import com.openclassrooms.payMyBuddy.service.UserService;

@Controller
public class OperationController

{

	@Autowired
	private OperationService operationService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/transaction")
	
	public String sendAmountToContact(Model model, float transactionAmount,String email)
	{
		
		try
		{
			model.addAttribute("logUser", userService.findLogUser());
		//model.addAttribute("operation", operationDTO);
			operationService.transaction(transactionAmount, userService.findLogUser(), email);
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
		
		return "redirect:/";
	}
}
