package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.TypeReferences.EntityModelType;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	private UserDaoService service;
	
	public UserResource(UserDaoService userDaoService) {
		this.service=userDaoService;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUserById(@PathVariable Integer id){
		
		User user = service.findOne(id);

		if(user == null) {
			throw new UserNotFoundException("id : " + id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User currentUser = service.save(user);
		
		URI location = 
				ServletUriComponentsBuilder.fromCurrentRequest()
											.path("/{id}")
											.buildAndExpand(currentUser.getId())
											.toUri();
		
		return ResponseEntity.created(location ).build();
	}
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Integer id){
		service.deleteById(id);
		 
	}
}