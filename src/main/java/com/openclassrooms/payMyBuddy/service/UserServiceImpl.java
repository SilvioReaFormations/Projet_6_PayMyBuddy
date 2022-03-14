package com.openclassrooms.payMyBuddy.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.payMyBuddy.model.Role;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User save(UserDTO userDTO)
	{
		User newUser = new User(userDTO.getFirstName(), 
				userDTO.getLastName(),
				userDTO.getEmail(), 
				passwordEncoder.encode(userDTO.getPassword()),
				Arrays.asList(new Role("ROLE_USER"))
				);
		return userRepository.save(newUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepository.findByEmail(username);
		
		if (user == null)
		{
			throw new UsernameNotFoundException("Invalid username or invalid Password");
		}
		
		return new org.springframework.security.core.userdetails.User
				(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
	{
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
	}
	

}
