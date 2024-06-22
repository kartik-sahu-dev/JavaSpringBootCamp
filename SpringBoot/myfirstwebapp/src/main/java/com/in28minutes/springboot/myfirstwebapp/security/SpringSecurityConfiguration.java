package com.in28minutes.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
	
		UserDetails userDetail = createNewUser("in28minutes", "dummy");
		UserDetails userDetail2 = createNewUser("Kartik", "dummy");
		
		return new InMemoryUserDetailsManager(userDetail,userDetail2);
	}
	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder =
				input -> passwordEncoder().encode(input);
		UserDetails userDeatail = User.builder()
									 .passwordEncoder(passwordEncoder)
									 .username(username)
									 .password(password)
									 .roles("USER","ADMIN")
									 .build();
		return userDeatail;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		return http.build();
	}
}
