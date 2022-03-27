package com.openclassrooms.payMyBuddy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openclassrooms.payMyBuddy.model.Operation;

/**
 * Repository interface which extends JpaRepository in order to use CRUD methods
 * @author Silvio
 *
 */


@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer>
{
	
	/**
	 * Native SQL request to find all the operation related to an email adress
	 * @param email
	 * @param pageable
	 * @return
	 */
	
	
	@Query(value="SELECT * FROM operation as operation "
	+ "JOIN user as user ON operation.sender=user.id "
	+ "WHERE user.email=:email", nativeQuery=true)
public Page<Operation> findAllByUser(@Param("email") String email, Pageable pageable);

}
