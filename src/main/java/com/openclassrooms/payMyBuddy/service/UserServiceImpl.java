package com.openclassrooms.payMyBuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User save(UserDTO userDTO)
	{
		User newUser = new User(userDTO.getFirstName(), 
				userDTO.getLastName(),
				userDTO.getEmail(), 
				userDTO.getPassword());
		return userRepository.save(newUser);
	}
	

}
