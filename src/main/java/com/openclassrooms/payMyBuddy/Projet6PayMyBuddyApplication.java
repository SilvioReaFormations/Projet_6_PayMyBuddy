package com.openclassrooms.payMyBuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


// EXCLUSION DE L4AUTOCONFIG SECURITY POUR SKIP LA PAGE DE LOGIN AU LANCEMENT
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Projet6PayMyBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(Projet6PayMyBuddyApplication.class, args);
	}

}
