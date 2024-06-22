package com.in28minutes.learnspringframework.examples.d1;


import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class ClassA{
	
}
@Component
@Lazy
class ClassB{
	private ClassA classA;
	
	public ClassB(ClassA classA){
		System.out.println("Some intialization logic");
		this.classA = classA;
	}
	public void doSomething() {
		System.out.println("do something");
	}
}


@Configuration
@ComponentScan
public class LazyInitializationLauncherApplication {
	 
	public static void main(String[] args) {
		 
		try(var context = 
				new AnnotationConfigApplicationContext
					(LazyInitializationLauncherApplication.class)){
		
			context.getBean(ClassB.class).doSomething();
		}
		
	}
}
