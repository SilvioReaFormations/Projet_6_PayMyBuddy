package com.openclassrooms.payMyBuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.payMyBuddy.exception.TransactionException;
import com.openclassrooms.payMyBuddy.model.Charges;
import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.ChargesRepository;
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
	
	@Autowired
	private ChargesRepository chargesRepository;
	
	

	
	/**
	 * Method which find a receiver with his email adress and sent to him an account. Can add a description
	 */
	
	public Operation transaction(Double amount, User sender, String email, String description) throws TransactionException

	{
		Charges charges = new Charges();
		
		
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
			opDTO.setAmount(amount*charges.getCHARGES());
			opDTO.setDescription(description);

			Operation operation = new Operation(opDTO.getAmount(), opDTO.getSender(), opDTO.getReceiver(), opDTO.getDescription());

			sender.setAccount(sender.getAccount() - amount);
			receiver.setAccount(receiver.getAccount() + amount*charges.getCHARGES());

			userRepository.save(sender);
			userRepository.save(receiver);
			operationRepository.save(operation);
			
			
			charges.setOperation(operation);
			charges.setChargesAmount(amount - (amount*charges.getCHARGES()) );
			chargesRepository.save(charges);
			
			return operation;
		
	}
	
	
	

}
