package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	
	private static int userCount = 0;
	
	static {
		users.add(new User(++userCount,"Kartik",LocalDate.now().minusYears(23)));
		users.add(new User(++userCount,"Ranga",LocalDate.now().minusYears(23)));
		users.add(new User(++userCount,"Adam",LocalDate.now().minusYears(23)));
	}
	
	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
	public List<User> findAll(){
		return users;
	}

	public User findOne(Integer id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id) ;
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		Predicate<? super User> predicate = user -> user.getId().equals(id) ;
		users.removeIf(predicate);
	}
}
