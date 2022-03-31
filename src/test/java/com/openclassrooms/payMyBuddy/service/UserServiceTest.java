package com.openclassrooms.payMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.openclassrooms.payMyBuddy.exception.ContactException;
import com.openclassrooms.payMyBuddy.exception.TransactionException;
import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.model.Roles;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.OperationRepository;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
	
	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepo;
	
	@Mock
	OperationRepository opeRepo;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	User usertest= new User ("TEST", "Test", "emailTest", "passwordTest", Roles.USER);
	User contactTest = new User ("TESTCONTACT", "TestContact", "emailTestContact", "passwordTestContact", Roles.USER);
	
	
	
	@Test
	void addAmountToAccountTest() throws Exception
	{

		userService.udpateAccount(usertest, 10.0);
		assertEquals(10.0, usertest.getAccount());
	}
	
	
	@Test
	public void addAmountToAccountNegativeExceptionTest() throws Exception
	{
		
		assertThrows(TransactionException.class, () -> {
			userService.udpateAccount(usertest, (double) -1);
		});
	}
	
	@Test
	public void addAmountToAccountNullExceptionTest() throws Exception
	{
		assertThrows(TransactionException.class, () -> {
			userService.udpateAccount(usertest, 0.0);
		});
	}
	
	@Test
	public void addContactTest() throws Exception
	{
		when(userRepo.findByEmail("emailTestContact")).thenReturn(contactTest);
		when(userRepo.save(any())).thenReturn(usertest);
		userService.addContact(usertest, contactTest.getEmail());
		assertTrue(usertest.getContact().contains(contactTest));
	}

	@Test
	public void addContactExceptionTestContactIsUser() throws Exception
	{
			when(userRepo.findByEmail("emailTest")).thenReturn(usertest);
			
			assertThrows(ContactException.class, () -> {
			userService.addContact(usertest, "emailTest");
		});
	}

	@Test
	public void addContactExceptionTestContactAlreadyInList() throws Exception
	{
			when(userRepo.findByEmail("emailContactTest")).thenReturn(contactTest);
			
			usertest.getContact().add(contactTest);
			
			assertThrows(ContactException.class, () -> {
			userService.addContact(usertest, "emailContactTest");
		});
	}
	
	@Test
	public void addContactExceptionTestNullContact() throws Exception
	{
			assertThrows(ContactException.class, () -> {
			userService.addContact(usertest, null);
		});
	}
	
	
	@Test
	public void saveNewUserTest()
	{
		when(passwordEncoder.encode("passwordTest")).thenReturn("passwordTest");
		
		UserDTO userDTOTest = new UserDTO("TEST", "Test", "emailTest", "passwordTest");
		User user = userService.saveNewUser(userDTOTest);
		
		assertTrue(user.getEmail().equals("emailTest"));
	}
	
	
	@Test
	public void loadUserByUsernameTest()
	{
		when(userRepo.findByEmail("emailTest")).thenReturn(usertest);
		
		UserDetails userDetails = userService.loadUserByUsername("emailTest");
		
		assertTrue(userDetails.getUsername().equals(usertest.getEmail()));
		assertTrue(userDetails.getPassword().equals(usertest.getPassword()));
	}
	
}
