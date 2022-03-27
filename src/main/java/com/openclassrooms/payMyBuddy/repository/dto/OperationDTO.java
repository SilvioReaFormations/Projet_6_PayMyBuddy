package com.openclassrooms.payMyBuddy.repository.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.openclassrooms.payMyBuddy.model.User;

/**
 * Data Transfert Object Class for Operation
 * @author Silvio
 *
 */


public class OperationDTO implements Serializable
{

	private static final long serialVersionUID = 1L;
	private double amount;
	private String description;
	private User sender;
	private User receiver;
	
	
	public OperationDTO(double amount, User sender, User receiver)
	{
		super();
		this.amount = amount;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	
	public OperationDTO()
	{
		super();
	}
	
	
	
	
	
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public User getSender()
	{
		return sender;
	}
	public void setSender(User sender)
	{
		this.sender = sender;
	}
	public User getReceiver()
	{
		return receiver;
	}
	public void setReceiver(User receiver)
	{
		this.receiver = receiver;
	}
	
	
	
	
	
}
