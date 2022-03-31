package com.openclassrooms.payMyBuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.payMyBuddy.exception.TransactionException;
import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.OperationRepository;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import com.openclassrooms.payMyBuddy.repository.dto.OperationDTO;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;

/**
 * Implementation of the OperationService interface
 * @author Silvio
 *
 */


@Service
public class OperationServiceImpl implements OperationService
{
	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private UserRepository userRepository;

	
	/**
	 * Method which find a receiver with his email adress and sent to him an account. Can add a description
	 */
	
	public Operation transaction(Double amount, User sender, String email, String description) throws TransactionException

	{
			if (amount <=0 || amount.equals(null))
			{
				throw new TransactionException("Please enter a valid amount");
			}
			
			
			if (sender.getAccount() < amount)
			{
				throw new TransactionException("Non-sufficient funds");
			}
		
			User receiver = userRepository.findByEmail(email);
			
			
			if(receiver == null)
			{
				throw new TransactionException("Invalid contact");
			}
			
			
			OperationDTO opDTO = new OperationDTO();
			opDTO.setSender(sender);
			opDTO.setReceiver(receiver);
			opDTO.setAmount(amount*0.95);
			opDTO.setDescription(description);

			Operation operation = new Operation(opDTO.getAmount(), opDTO.getSender(), opDTO.getReceiver(), opDTO.getDescription());

			sender.setAccount(sender.getAccount() - amount);
			receiver.setAccount(receiver.getAccount() + amount*0.95);

			userRepository.save(sender);
			userRepository.save(receiver);
			operationRepository.save(operation);
			
			return operation;
		
	}
	
	
	

}
