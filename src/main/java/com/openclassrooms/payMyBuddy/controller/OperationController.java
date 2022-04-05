package com.openclassrooms.payMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.payMyBuddy.exception.TransactionException;
import com.openclassrooms.payMyBuddy.model.Credit;
import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.dto.OperationDTO;
import com.openclassrooms.payMyBuddy.service.OperationService;
import com.openclassrooms.payMyBuddy.service.UserService;


/**
 * OperationController class
 * @author Silvio
 *
 */

@Controller
public class OperationController

{

	@Autowired
	private OperationService operationService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/transaction")
	public String sendAmountToContact(Model model, @RequestParam(defaultValue = "0") double transactionAmount,String email, String description,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size,
			@RequestParam(name="pageCredit", defaultValue="0") int pageC,
			@RequestParam(name="sizeCredit", defaultValue="5") int sizeC)
	{
		model.addAttribute("logUser", userService.findLogUser());
		Page<Operation> pageOperation = userService.findOperationByUser(page, size);
		model.addAttribute("list", pageOperation.getContent() );
		model.addAttribute("pages", new int[pageOperation.getTotalPages()]);
		model.addAttribute("currentPage", page);
		
		Page<Credit> pageCredit = userService.findCreditByUser(pageC, sizeC);
		model.addAttribute("Creditlist", pageCredit.getContent() );
		model.addAttribute("Creditpages", new int[pageCredit.getTotalPages()]);
		model.addAttribute("CreditCurrentPage", pageC);
		
			
			try
			{
				Operation operation = operationService.transaction(transactionAmount, userService.findLogUser(), email, description);
				model.addAttribute("transactionAmount", transactionAmount);
				model.addAttribute("transactionReceiver", operation.getReceiver().getLastName() );
			} 
			
			catch (TransactionException e)
			{
				String exception = e.getMessage();
				model.addAttribute("exception1", exception);
				return "index";
			}
	
			return "redirect:/?transactionSuccess";
	}
}
