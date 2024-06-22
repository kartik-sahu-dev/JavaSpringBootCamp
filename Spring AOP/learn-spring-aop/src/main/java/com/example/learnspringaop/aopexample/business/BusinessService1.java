package com.example.learnspringaop.aopexample.business;

import java.util.Arrays;
 
import org.springframework.stereotype.Service;

import com.example.learnspringaop.aopexample.annotations.TrackTime;
import com.example.learnspringaop.aopexample.data.DataService1;

@Service
public class BusinessService1 {
	private DataService1 dataService;
	
	@TrackTime
	public int calculateMax() {
		int[] data = dataService.retrieveData();
//		throw new RuntimeException("Something went wrong");
		return Arrays.stream(data).max().orElse(0);
	}

	public BusinessService1(DataService1 dataService) {
		super();
		this.dataService = dataService;
	}
}
