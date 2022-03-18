package com.openclassrooms.payMyBuddy.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;

public interface UserService extends UserDetailsService
{
	public User save(UserDTO userDTO);
	public User findLogUser();
	public User udpateAccount(User userNewAccount, float amount);
	public User addContact(User user, String email);
	
}
