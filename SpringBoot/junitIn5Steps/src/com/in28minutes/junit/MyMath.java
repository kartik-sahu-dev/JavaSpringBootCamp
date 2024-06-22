package com.in28minutes.junit;

public class MyMath {
	public int calculateSum(int[] num) {
		int sum = 0;
		for(int n : num) {
			sum += n;
		}
		return sum;
	}
}
