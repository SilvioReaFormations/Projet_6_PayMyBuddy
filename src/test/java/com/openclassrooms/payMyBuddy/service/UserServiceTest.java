package com.openclassrooms.payMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.payMyBuddy.model.Roles;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
	
	@InjectMocks
	UserServiceImpl userService;
	
	
	@Mock
	UserRepository userRepo;
	
	User usertest= new User ("TEST", "Test", "emailTest", "passwordTest", Roles.USER);
	User contactTest = new User ("TESTCONTACT", "TestContact", "emailTestContact", "passwordTestContact", Roles.USER);
	
	

	@Test
	void addAmountToAccountTest() throws Exception
	{
		when(userRepo.save(any())).thenReturn(usertest);
		userService.udpateAccount(usertest, (double) 10);
		assertEquals(10, usertest.getAccount());
	}
	
	
	@Test
	void addContactTest() throws Exception
	{
		when(userRepo.findByEmail("emailTestContact")).thenReturn(contactTest);
		when(userRepo.save(any())).thenReturn(usertest);
		userService.addContact(usertest, contactTest.getEmail());
		assertTrue(usertest.getContact().contains(contactTest));
	}
	
	
}
