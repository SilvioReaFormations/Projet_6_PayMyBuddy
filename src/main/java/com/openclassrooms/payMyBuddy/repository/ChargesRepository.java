package com.openclassrooms.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.openclassrooms.payMyBuddy.model.Charges;

public interface ChargesRepository extends JpaRepository<Charges, Integer>
{

}
