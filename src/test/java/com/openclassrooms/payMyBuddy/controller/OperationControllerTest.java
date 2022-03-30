package com.openclassrooms.payMyBuddy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.payMyBuddy.model.Roles;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.service.OperationService;
import com.openclassrooms.payMyBuddy.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class OperationControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private UserService userService;
	

	
	@Test
	public void showCreateAccountFormTest() throws Exception
	{
		
		mockMvc.perform(get("/transaction"))
			.andExpect(status().isOk())
			.andExpect(view().name("redirect:/?transactionSuccess"));
		//	.andExpect(model().attribute("newUser", ));
	}
}
