package com.in28minutes.learnspringframework.examples.h1;


import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class XmlConfigurationContextLauncherApplication {
	 
	public static void main(String[] args) {
		 
		try(var context = 
				new AnnotationConfigApplicationContext
					(XmlConfigurationContextLauncherApplication.class)){
			 Arrays.stream(context.getBeanDefinitionNames())
			 	.forEach(System.out::println);
		}
	}
}
