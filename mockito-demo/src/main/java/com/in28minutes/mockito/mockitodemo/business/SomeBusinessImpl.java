package com.in28minutes.mockito.mockitodemo.business;

public class SomeBusinessImpl {
	
	private DataService dataSource;
	public SomeBusinessImpl(DataService dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public int findTheGreatestFromAllData() {
		int data[]= dataSource.retrieveAllData(); 
		int greatestValue = Integer.MIN_VALUE;
		for(int val : data) {
			if(val > greatestValue) {
				greatestValue = val;
			}
		}
		
		return greatestValue;
	}

	
}

interface DataService{
	int[] retrieveAllData();
}
