package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	public boolean authenticate(String name, String password){
		boolean isValidUsername = name.equalsIgnoreCase("in28minutes");
		boolean isValidPassword = password.equalsIgnoreCase("dummy");
		
		return isValidUsername && isValidPassword;
	}
}
