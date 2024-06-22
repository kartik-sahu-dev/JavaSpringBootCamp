package com.in28minutes.mockito.mockitodemo.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListTest {
	
	@Test
 	void simpleTest() {
		List listMock =mock(List.class);
		when(listMock.size()).thenReturn(3);
		
		assertEquals(3,listMock.size());
	}
	
	@Test
	void multipleReturn() {
		List listMock =mock(List.class);
		when(listMock.size()).thenReturn(1).thenReturn(2);
		
		assertEquals(1,listMock.size());
		assertEquals(2,listMock.size());
		assertEquals(2,listMock.size());//last return value will be default return value, it doesn't matter how many times we call that method
		assertEquals(2,listMock.size());
	}
	@Test
	void parameters() {
		List listMock =mock(List.class);
		when(listMock.get(0)).thenReturn("some string");
		
		assertEquals("some string",listMock.get(0));
		assertEquals(null,listMock.get(1)); //by default it will return null
	}
	
	@Test
	void genericParameters() {
		List listMock =mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("some string");
		
		assertEquals("some string",listMock.get(0));
		assertEquals("some string",listMock.get(1)); // now it will return "some string" 
		
	}

}
