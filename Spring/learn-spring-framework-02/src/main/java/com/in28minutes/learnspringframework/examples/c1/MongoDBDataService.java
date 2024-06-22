package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.stereotype.Component;

@Component
public class MongoDBDataService implements DataService{
	
	public int[] retrieveData() {
		return new int[] {11,22,33,44,55};
	}
}