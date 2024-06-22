package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockTest2 {

	@Mock
	private DataService dataServiceMock;
	@InjectMocks
	private SomeBusinessImpl businessImpl;
	
	@Test
	void findTheGreatestFromAllData_basicScenario() { 
		 
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {34,43,22});
		 
		assertEquals(43,businessImpl.findTheGreatestFromAllData());
		
	}
	@Test
	void findTheGreatestFromAllData_oneValue() { 
		 when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {34});
		 int result = businessImpl.findTheGreatestFromAllData();
		
		 assertEquals(34,result);
		
	}
 
}
 