package com.in28minutes.rest.webservices.restfulwebservices.jpa;

 
import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;

public interface JPARepository extends JpaRepository<User,Integer>{

}
