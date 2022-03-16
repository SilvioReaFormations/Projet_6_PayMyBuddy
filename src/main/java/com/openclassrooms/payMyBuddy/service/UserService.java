package com.openclassrooms.payMyBuddy.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;

public interface UserService extends UserDetailsService
{
	User save(UserDTO userDTO);
	
	// User udpateAccount(int userId, float amount);
	
}
