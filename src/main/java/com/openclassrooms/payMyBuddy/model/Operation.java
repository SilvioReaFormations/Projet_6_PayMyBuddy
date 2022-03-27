package com.openclassrooms.payMyBuddy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Operation Entity
 * @author Silvio
 *
 */

@Entity
@Table(name = "operation")
public class Operation
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "sender")
	private User sender;
	
	@ManyToOne(cascade= CascadeType.ALL) 
	@JoinColumn(name = "receiver")
	private User receiver;

	
	public Operation()
	{
		super();
	}

	public Operation(double amount, User sender, User receiver, String description)
	{
		super();
		this.amount = amount;
		this.sender = sender;
		this.receiver = receiver;
		this.description = description;
	}

	
	
	
	
	// GETTER / SETTER


	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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
