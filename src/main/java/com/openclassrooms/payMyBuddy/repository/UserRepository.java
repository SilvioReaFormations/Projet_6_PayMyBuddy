package com.openclassrooms.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openclassrooms.payMyBuddy.model.User;

/**
 * Repository interface which extends JpaRepository in order to use CRUD methods
 * @author Silvio
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	
	/**
	 * native SQL request to find a user with his email.
	 * @param email
	 * @return
	 */
	
	@Query(value="SELECT * FROM user WHERE email=:email", nativeQuery=true)
	User findByEmail(@Param("email") String email);
	
}
