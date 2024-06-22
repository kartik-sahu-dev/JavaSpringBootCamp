package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SomeBusinessImplStubTest {

	@Test
	void findTheGreatestFromAllData_basicScenario() { 
		DataService dataServiceStub = new DataServiceStub();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
		int result = businessImpl.findTheGreatestFromAllData();
		
		assertEquals(34,result);
		
	}
	@Test
	void findTheGreatestFromAllData_withOneValue() { 
		DataService dataServiceStub1 = new DataServiceStub();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub1);
		int result = businessImpl.findTheGreatestFromAllData();
		
		assertEquals(34,result);
		
	}

}

class DataServiceStub implements DataService{
	public int[] retrieveAllData() {
		return new int[] {34,32,1};
	}
}
class DataServiceStub1 implements DataService{
	public int[] retrieveAllData() {
		return new int[] {34};
	}
}