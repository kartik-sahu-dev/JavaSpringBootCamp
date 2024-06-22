package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList();
	private static int todoCounts = 0;
	
	static {

		todos.add(new Todo(++todoCounts,"in28minutes","Learn AWS 1",
				LocalDate.now().plusYears(1),false));

		todos.add(new Todo(++todoCounts,"in28minutes","Learn Azure 1",
				LocalDate.now().plusYears(2),false));
		
		todos.add(new Todo(++todoCounts,"in28minutes","Learn DevOps 1",
				LocalDate.now().plusYears(3),false));
	}
	
	public List<Todo> findByUserName(String userName){
		System.out.println(userName);
		Predicate<? super Todo> predicate
					= todo -> todo.getUserName().equalsIgnoreCase(userName);
	
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String userName, String description, LocalDate targetDate, boolean done){
		todos.add(new Todo(++todoCounts,userName,description,targetDate,false));
		
	}

	public void deleteById(int id) {

		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		
		todos.removeIf(predicate); 

	}

	public Todo findById(int id) {
		
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate ).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
