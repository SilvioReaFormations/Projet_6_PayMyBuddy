package com.openclassrooms.payMyBuddy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Charges entity
 * @author Silvio
 *
 */

@Entity
@Table(name = "charges")
public class Charges
{
	
	private final double CHARGES = 0.95;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "charge")
	private double chargesAmount;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "operation")
	private Operation operation;

	
	
	
	
	
	public Charges()
	{
		super();
	}

	public Charges(double chargesAmount, Operation operation)
	{
		super();
		this.chargesAmount = chargesAmount;
		this.operation = operation;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public double getChargesAmount()
	{
		return chargesAmount;
	}

	public void setChargesAmount(double chargesAmount)
	{
		this.chargesAmount = chargesAmount;
	}

	public Operation getOperation()
	{
		return operation;
	}

	public void setOperation(Operation operation)
	{
		this.operation = operation;
	}

	public double getCHARGES()
	{
		return CHARGES;
	}
	

	
	
	



	
	
	
}
