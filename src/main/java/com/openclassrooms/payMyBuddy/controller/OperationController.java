package com.openclassrooms.payMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.payMyBuddy.exception.TransactionException;
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
	public String sendAmountToContact(Model model, float transactionAmount,String email, String description) throws TransactionException
	{
		
			model.addAttribute("logUser", userService.findLogUser());
			try
			{
				operationService.transaction(transactionAmount, userService.findLogUser(), email, description);
			} 
			
			catch (TransactionException e)
			{
				String exception = e.getMessage();
				model.addAttribute("exception1", exception);
			}
	
			return "index";
	}
}
