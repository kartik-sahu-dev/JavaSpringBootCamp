package com.in28minutes.learnspringframework.examples.c1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessCalculationClass {

	
	DataService dataService;
	
	//Constructor injection
	public BusinessCalculationClass(DataService dataService) {
		super();
		this.dataService = dataService;
	}

	public int findMax() {
		return Arrays.stream(dataService.retrieveData())
				.max().orElse(0);
	}
}
