package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.QuizRepository;
import com.exam.service.UserService;

@SpringBootApplication
public class FinalExamserverApplication implements CommandLineRunner{
	
	    @Autowired
	    private UserService userService;

	    @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    @Autowired
	    public QuizRepository quizRepository;

	    public static void main(String[] args) {


	        SpringApplication.run(FinalExamserverApplication.class, args);


	    }

	    @Override
	    public void run(String... args) throws Exception {
	        try {


	            System.out.println("starting code");

	            User user = new User();

	            user.setFirstName("Admin");
	            user.setLastName("User");
	            user.setUsername("admin1105");
	            user.setPassword(this.bCryptPasswordEncoder.encode("abcde"));
	            user.setEmail("user@gmail.com");
	            user.setProfile("default.png");

	            Role role1 = new Role();
	            role1.setRoleId(101L);
	            role1.setRoleName("ADMIN");

	            Set<UserRole> userRoleSet = new HashSet<>();
	            UserRole userRole = new UserRole();

	            userRole.setRole(role1);

	            userRole.setUser(user);

	            userRoleSet.add(userRole);

	            User user1 = this.userService.createUser(user, userRoleSet);
	            System.out.println(user1.getUsername());


	        } catch (UserFoundException e) {
	            e.printStackTrace();


	       }


	    }


	}
