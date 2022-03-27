package com.openclassrooms.payMyBuddy.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.payMyBuddy.exception.ContactException;
import com.openclassrooms.payMyBuddy.exception.TransactionException;
import com.openclassrooms.payMyBuddy.model.Operation;
import com.openclassrooms.payMyBuddy.model.Roles;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.OperationRepository;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;

/**
 * Implementation of the UserService interface
 * @author Silvio
 *
 */

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OperationRepository operationRepository;

// Lazy pour une injection parraisseuse afin d'éviter la boucle d'injection.
	@Lazy
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
		
	public Page<Operation> findOperationByUser(int page, int size)
		{
			Pageable pageable = PageRequest.of(page, size);
			User user = findLogUser();
			return operationRepository.findAllByUser(user.getEmail(), pageable);
		}
		
		
	
	public User saveNewUser(UserDTO userDTO) throws SQLIntegrityConstraintViolationException
	{
		User newUser = new User(userDTO.getFirstName(), 
				userDTO.getLastName(),
				userDTO.getEmail(), 
				passwordEncoder.encode(userDTO.getPassword()),
				Roles.USER
				);
		
		return userRepository.save(newUser);
	}
	

	

	public User udpateAccount(User userNewAccount, Double amount) throws TransactionException
	{
		if (amount <=0)
		{
			throw new TransactionException("Invalid amount");
		}
		
		if (amount.equals(null))
		{
			throw new TransactionException("Please choose an amount");
		}
		
		userNewAccount.setAccount(userNewAccount.getAccount() + amount);
		userRepository.save(userNewAccount);
		return userNewAccount;
	}
	
	
	public User addContact(User user, String email) throws ContactException
	{
		User newContact = userRepository.findByEmail(email);
		
		if ( user.getContact().contains(newContact) )
		{
			throw new ContactException("Contact already exist");
		}
		
		if( user.equals(newContact) )
		{
			throw new ContactException("You can't add yourself :-)");
		}
		
		if( newContact == null )
		{
			throw new ContactException("This user does't exist");
		}
		
		user.getContact().add(newContact);
		userRepository.save(user);
		
		return user;
	}
	
	
	
	
	/**
	 * Method which return the user who is currently log in
	 */
	
	public User findLogUser()
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String logUserEmail = auth.getName();
			User logUser = userRepository.findByEmail(logUserEmail);
			return logUser;
		}
	
	/**
	 * Method wich allows to create a secure connection
	 */
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepository.findByEmail(username);
		
		if (user == null)
		{
			throw new UsernameNotFoundException("Invalid username or invalid Password");
		}
// On a besoin de la classe User de Spring security et non celle de l'Entité donc il faut 
//	lui indiquer que c'est celle la qu'on veut en indiquant le lien complet avec le package 
		return new org.springframework.security.core.userdetails.User
				(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
	}
	
// ? pour dire que la collection correspond à toutes les classes filles de GrantedAuthority
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Roles role)
	{
		List<SimpleGrantedAuthority> authorithies = new ArrayList<>();
	    authorithies.add(new SimpleGrantedAuthority(role.name()));
		return authorithies;
		
	}
	
	



}
