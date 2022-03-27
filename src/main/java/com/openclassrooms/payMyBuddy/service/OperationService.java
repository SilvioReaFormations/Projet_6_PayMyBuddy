package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.exception.TransactionException;
import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.dto.OperationDTO;

public interface OperationService

{
	public Operation transaction(Double Amount, User sender, String email, String description) throws TransactionException;
}
