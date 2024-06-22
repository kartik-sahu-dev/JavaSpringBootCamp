package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.jpa.JPARepository;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/jpa")
public class UserJpaResource {
	private JPARepository userRepository;
	private PostRepository postRepository;
	
	public UserJpaResource (JPARepository userRepository,PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUserById(@PathVariable Integer id){
		
		Optional<User> user = userRepository.findById(id);

		if(user.isEmpty()) {
			throw new UserNotFoundException("id : " + id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User currentUser = userRepository.save(user);
		
		URI location = 
				ServletUriComponentsBuilder.fromCurrentRequest()
											.path("/{id}")
											.buildAndExpand(currentUser.getId())
											.toUri();
		
		return ResponseEntity.created(location ).build();
	}
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Integer id){
		userRepository.deleteById(id);
		 
	}
	@GetMapping("/users/{id}/posts")
	public List<Post> retrievePostForUser(@PathVariable Integer id){

		Optional<User> user = userRepository.findById(id);

		if(user.isEmpty()) {
			throw new UserNotFoundException("id : " + id);
		}
		

		return user.get().getPosts();
		 
	}
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable Integer id,@Valid @RequestBody Post post){

		Optional<User> user = userRepository.findById(id);

		if(user.isEmpty()) {
			throw new UserNotFoundException("id : " + id);
		}
		post.setUser(user.get());

		Post savedPost = postRepository.save(post);

		URI location = 
				ServletUriComponentsBuilder.fromCurrentRequest()
											.path("/{id}")
											.buildAndExpand(savedPost.getId())
											.toUri();
		
		return ResponseEntity.created(location ).build();

		 
	}
}