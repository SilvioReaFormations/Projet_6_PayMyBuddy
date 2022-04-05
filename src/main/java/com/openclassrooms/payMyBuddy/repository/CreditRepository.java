package com.openclassrooms.payMyBuddy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.openclassrooms.payMyBuddy.model.Credit;
import com.openclassrooms.payMyBuddy.model.Operation;

public interface CreditRepository extends JpaRepository<Credit, Integer>
{
	@Query(value="SELECT * FROM credit as credit "
			+ "JOIN user as user ON user_id=user.id "
			+ "WHERE user.email=:email", nativeQuery=true)
		public Page<Credit> findCreditByUser(@Param("email") String email, Pageable pageable);	
}
