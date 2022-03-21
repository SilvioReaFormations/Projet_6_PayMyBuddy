package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.dto.OperationDTO;

public interface OperationService

{
	public Operation transaction(float Amount, User sender, String email) throws Exception;
}
