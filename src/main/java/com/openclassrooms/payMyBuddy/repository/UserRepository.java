package com.openclassrooms.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.payMyBuddy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	User findByEmail(String email);
}
