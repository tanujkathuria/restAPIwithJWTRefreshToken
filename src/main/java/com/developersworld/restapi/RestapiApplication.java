package com.developersworld.restapi;

import com.developersworld.restapi.entities.User;
import com.developersworld.restapi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RestapiApplication implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
       this.userRepo.save(new User(1, "ram",
			   new BCryptPasswordEncoder().encode("ram@123"), "USER"));
		this.userRepo.save(new User(2, "admin",
				new BCryptPasswordEncoder().encode("admin@123"), "ADMIN"));
	}
}
