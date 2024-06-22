package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	private TodoRepository todoRepository;
	
	public TodoControllerJpa(TodoRepository todoRepository ) {
		super();
		this.todoRepository = todoRepository;
	 }

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model ){
		String username = getLoggedInUsername( );
//		List<Todo> todos = todoService.findByUserName(username);
		List<Todo> todos = todoRepository.findByUserName(username);
		model.addAttribute("todos",todos);
		
		return "listTodos";
	}

	private String getLoggedInUsername( ) {
		Authentication authentication 
				= SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model){
		
		String username = getLoggedInUsername();
		Todo todo = new Todo(0,username,"Default Description",LocalDate.now().plusYears(1),false);
		model.put("todo", todo);
		return "todo";
	}

//	@RequestMapping(value="add-todo", method = RequestMethod.POST)
//	public String addNewTodo(@RequestParam String description, ModelMap model){
//		String userName = (String)model.get("name");
//		todoService.addTodo(userName, description, LocalDate.now().plusYears(1), false);
//		return "redirect:list-todos";
//	}
	
	//using command bean (form backing object)
	
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo ,BindingResult result){
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String userName = getLoggedInUsername();
//		todoService.addTodo(userName, todo.getDescription(), todo.getTargetDate(), false);

		todo.setUserName(userName);
		todoRepository.save(todo);

		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id){
	
//		todoService.deleteById(id);
		todoRepository.deleteById(id);
		
		return "redirect:list-todos";
 		
 	}

	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model){
		model.addAttribute("todo",todoRepository.findById(id).get());
		return "todo";	
	}

	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String UpdateTodo(ModelMap model,@Valid Todo todo, BindingResult result){
		if(result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUsername();
		todo.setUserName(username);
//		todoService.updateTodo(todo);
		todoRepository.save(todo);
		return "redirect:list-todos";	
	}
}
