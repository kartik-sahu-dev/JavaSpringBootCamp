package com.example.learnoauth.welcome;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {
	
	@GetMapping("/")
	public String welcomeBack() {
		
		return "<h3 style='text-center'> Hello World!!! </h3>";
	}
}
