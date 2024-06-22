package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class AssertTest {
	List<String> list = Arrays.asList("AWS","GCP","AZURE");
	
	@Test
	void test() {
		
		assertEquals(true,list.contains("AWS"));
		assertEquals(false,list.contains("DevOps"));
		assertTrue(list.contains("AWS"));
		assertFalse(list.contains("DevOps"));
		assertArrayEquals(new int[] {1,2,3},new int[] {1,3,4});
	}

}