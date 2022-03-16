package com.openclassrooms.payMyBuddy.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "user")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	
	private Float account;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private Roles role;
	
	@ManyToMany
	@JoinTable(
			name = "contact",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "contactUser_id"))
	List <User> contact = new ArrayList<>();

	
	
	
	// CONSTRUCTORS //
	



	public User(String firstName, String lastName, String email, String password,
			Roles role)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	


	public User()
	{
		
	}
	
	
	
	// GETTER / SETTER

	

	public Roles getRole()
	{
		return role;
	}



	public void setRole(Roles role)
	{
		this.role = role;
	}

	
	

	public int getId()
	{
		return id;
	}

	public String getEmail()
	{
		return email;
	}



	public void setEmail(String email)
	{
		this.email = email;
	}


	public void setId(int id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}


	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Float getAccount()
	{
		return account;
	}

	public void setAccount(Float account)
	{
		this.account = account;
	}

	public List<User> getContact()
	{
		return contact;
	}

	public void setContact(List<User> contact)
	{
		this.contact = contact;
	}
	
	
	
	

	
	
	
	
	
	
	
	 
}
