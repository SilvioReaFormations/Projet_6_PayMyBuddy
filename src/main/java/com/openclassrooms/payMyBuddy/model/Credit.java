package com.openclassrooms.payMyBuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Credit Entity
 * @author Silvio
 *
 */


@Entity
@Table(name = "credit")
public class Credit
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "amount")
	private double amount;
	

	
	
	public Credit()
	{
		super();
	}


	public Credit(double amount)
	{
		super();
		this.amount = amount;
	}




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


	
	
	
	
	
	
}
