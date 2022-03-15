package com.openclassrooms.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.payMyBuddy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	// IA de SPRING S2CURITY defini elle meme le query avec le nom de la méthode
	User findByEmail(String email);
}
