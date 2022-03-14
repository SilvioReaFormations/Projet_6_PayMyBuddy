package com.openclassrooms.payMyBuddy.service;

import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.dto.UserDTO;

public interface UserService
{
	User save(UserDTO userDTO);
}
