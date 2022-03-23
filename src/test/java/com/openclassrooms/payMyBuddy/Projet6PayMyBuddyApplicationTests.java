package com.openclassrooms.payMyBuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.payMyBuddy.model.Roles;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import com.openclassrooms.payMyBuddy.service.OperationService;
import com.openclassrooms.payMyBuddy.service.UserService;

@SpringBootTest
class Projet6PayMyBuddyApplicationTests {

	@Autowired
	UserService userService;
	
	@Autowired
	OperationService operationService;
	
	@Mock
	UserRepository userRepo;
	
	User usertest= new User ("TEST", "Test", "emailTest", "passwordTest", Roles.USER);
	User contactTest = new User ("TESTCONTACT", "TestContact", "emailTestContact", "passwordTestContact", Roles.USER);
	
	@Test
	void contextLoads() {
	}
	
	
	
	@Test
	void addAmountToAccountTest() throws Exception
	{
		
		userService.udpateAccount(usertest, 10);
		assertEquals(10, usertest.getAccount());
	}
	
	
	// CE TEST NE PASSE PAS
	
	@Test
	void addContactTest() throws Exception
	{
		
		when(userRepo.findByEmail("emailTestContact")).thenReturn(contactTest);
		
		userService.addContact(usertest, contactTest.getEmail());
		
		assertTrue(usertest.getContact().contains(contactTest));
	}
	
	
	
	@Test
	void sendMoneyToContactTest() throws Exception
	{
		usertest.setAccount(10);
		operationService.transaction(8, usertest, "emailTestContact", null);
		when(userRepo.findByEmail("emailTestContact")).thenReturn(contactTest);
		
		assertTrue(usertest.getAccount() == 2);
		assertTrue(contactTest.getAccount() == 8);
	}
	

}
