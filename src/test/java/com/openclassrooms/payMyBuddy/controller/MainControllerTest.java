package com.openclassrooms.payMyBuddy.controller;

import static org.assertj.core.api.Assertions.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.beans.HasProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.payMyBuddy.model.Roles;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class MainControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void showCreateAccountFormTest() throws Exception
	{
		User userTest = new User("Silvio", "REA", "TestMail", "password", Roles.USER);
		
		mockMvc.perform(get("/registration"))
			.andExpect(status().isOk())
			.andExpect(view().name("createAccountForm"));
		//	.andExpect(model().attribute("newUser", ));
	}
	
	
	
	@Test
	public void loginFormTest() throws Exception
	{
		mockMvc.perform(get("/registration"))
			.andExpect(status().isOk());
	
	}
	
	
}
