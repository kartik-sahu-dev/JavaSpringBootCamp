package com.example.learnspringaop.aopexample.business;

import java.util.Arrays;
 
import org.springframework.stereotype.Service;

import com.example.learnspringaop.aopexample.data.DataService1;
import com.example.learnspringaop.aopexample.data.DataService2;

@Service
public class BusinessService2 {
	private DataService2 dataService;
	
	public int calculateMin() {
		int[] data = dataService.retrieveData();
//		throw new RuntimeException("Something went wrong");
		return Arrays.stream(data).min().orElse(0);
	}

	public BusinessService2(DataService2 dataService) {
		super();
		this.dataService = dataService;
	}
}
