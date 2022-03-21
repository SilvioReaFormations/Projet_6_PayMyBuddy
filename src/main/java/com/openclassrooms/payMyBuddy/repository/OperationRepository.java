package com.openclassrooms.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.payMyBuddy.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer>
{
	
}
