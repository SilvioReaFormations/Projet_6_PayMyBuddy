package com.openclassrooms.payMyBuddy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Component;

import com.openclassrooms.payMyBuddy.model.Roles;
import com.openclassrooms.payMyBuddy.model.User;


@SpringBootApplication
public class Projet6PayMyBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(Projet6PayMyBuddyApplication.class, args);
	}


}

/*

@Component
class CommandLineRunnerImpl implements CommandLineRunner
{

	@Override
	public void run(String... args) throws Exception
	{
		User user = new User("REA", "Silvio", "email", "password", Roles.USER);
		User user2 = new User("REA2", "Silvio2", "email2", "password2", Roles.USER);
		User user3 = new User("REA3", "Silvio3", "email3", "password3", Roles.USER);
		
		user.getContact().add(user2);
		user.getContact().add(user3);
		
		user.getContact().forEach(u -> System.out.println(u.getLastName())); 
		
	}
	
	
}


*/