package com.openclassrooms.payMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.payMyBuddy.model.Credit;
import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;
import com.openclassrooms.payMyBuddy.service.UserService;

/**
 * MainController with all GetMapping Request
 * @author Silvio
 *
 */

@Controller
public class MainController
{
	@Autowired
	UserService userService;

	
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
	public String home(Model model,
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
		
		return "index";
	}
	

}
