package com.openclassrooms.payMyBuddy.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.payMyBuddy.exception.TransactionException;
import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.model.Roles;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.OperationRepository;
import com.openclassrooms.payMyBuddy.repository.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OperationServiceTest
{

	@InjectMocks
	OperationServiceImpl operationService;
	
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	OperationRepository operationRepo;
	
	User usertest= new User ("TEST", "Test", "emailTest", "passwordTest", Roles.USER);
	User contactTest = new User ("TESTCONTACT", "TestContact", "emailTestContact", "passwordTestContact", Roles.USER);
	Operation opeTest = new Operation ();
	
	@Test
	void sendMoneyToContactTest() throws Exception
	{
		usertest.setAccount(20);
		when(userRepo.findByEmail("emailTestContact")).thenReturn(contactTest);
		when(userRepo.save(usertest)).thenReturn(usertest);
		when(userRepo.save(contactTest)).thenReturn(contactTest);
		operationService.transaction((double) 10, usertest, "emailTestContact", "Description Test");
		
		assertEquals(10, usertest.getAccount());
		assertEquals(9.5, contactTest.getAccount());
	}
	
	@Test
	void sendMoneyToContactNegativeAmountExceptionTest() throws Exception
	{
		assertThrows(TransactionException.class, () -> {
			operationService.transaction(-1.0, usertest, "emailTestContact", "Description Test");
		});
	}
	
	@Test
	void sendMoneyToContactNullAmountExceptionTest() throws Exception
	{
		assertThrows(TransactionException.class, () -> {
			operationService.transaction((double) 0, usertest, "emailTestContact", "Description Test");
		});
	}
	
	@Test
	void sendMoneyToContactNonSufficentFundsExceptionTest() throws Exception
	{
		usertest.setAccount(0.0);
		assertThrows(TransactionException.class, () -> {
			operationService.transaction((double) 5, usertest, "emailTestContact", "Description Test");
		});
	}
	
	@Test
	void sendMoneyToInvalidContactExceptionTest() throws Exception
	{
		assertThrows(TransactionException.class, () -> {
			operationService.transaction((double) 5, usertest, "invalidEmailContact", "Description Test");
		});
	}
}
