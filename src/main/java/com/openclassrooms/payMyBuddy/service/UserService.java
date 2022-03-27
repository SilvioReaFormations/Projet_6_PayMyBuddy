package com.openclassrooms.payMyBuddy.service;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.openclassrooms.payMyBuddy.exception.ContactException;
import com.openclassrooms.payMyBuddy.exception.TransactionException;
import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;

public interface UserService extends UserDetailsService
{
	public User saveNewUser(UserDTO userDTO) throws SQLIntegrityConstraintViolationException;
	public User findLogUser();
	public User udpateAccount(User userNewAccount, Double amount) throws TransactionException;
	public User addContact(User user, String email) throws ContactException;
	
	public Page<Operation> findOperationByUser(int page, int size);
	
}
