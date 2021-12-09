package com.nnk.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UsersService;

@SpringBootApplication
public class Application {
	
	@Autowired
	private UsersService usersService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	CommandLineRunner run() {
		return args -> {
			
			User user1 = new User();
			
			user1.setId(null);
			user1.setUsername("jd");
			user1.setPassword("@Azerty123");
			user1.setFullname("JohnDoe");
			user1.setRole("ADMIN");
			
			usersService.saveUser(user1);
			
		};
	}
}
