package com.shubham.springsecurityuseraccess;

import com.shubham.springsecurityuseraccess.controller.UserController;
import com.shubham.springsecurityuseraccess.dao.UserDetailRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringSecurityUseraccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityUseraccessApplication.class, args);
	}

}
