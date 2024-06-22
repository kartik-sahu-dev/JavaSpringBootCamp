package com.in28minutes.learnspringsecurity.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
@EnableMethodSecurity(jsr250Enabled=true,securedEnabled=true)
public class TodoResource {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final List<Todo> TODOS_LIST= List.of(new Todo("kartik","Learn java"),
			new Todo("kartik","Learn java"),new Todo("user","Learn java"));

	@GetMapping("/todos")
	public List<Todo> retrieveAllTodos() {
		return TODOS_LIST;
	}
	
	@GetMapping("/users/{username}/todos")
//	@PreAuthorize("hasRole('USER') and #username == authentication.name")
//	@PostAuthorize("returnObject.username == 'user'")
	@RolesAllowed({"ADMIN", "USER"})
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public Todo retrieveTodosForSpecificUser() {
		return TODOS_LIST.get(0);
	}
	@PostMapping("/users/{username}/todos")
	public void createTodo(@PathVariable("username") String username,@RequestBody Todo todo) {
		logger.info("creating {} for {}",todo,username);
	}
}

record Todo(String username, String description) {
	
}
