package functional_programming_with_java.com.programming;

import java.util.List;

public class FP01Functional {

	public static void main(String[] args) {

		List<Integer> numbers = List.of(12, 34, 53, 28, 23);
//		printAllNumbersInListFunctional(numbers);
//		printEvenNumbersInListFunctional(numbers);
		printEvenNumbersSquareInListFunctional(numbers);

	}

	private static void printEvenNumbersSquareInListFunctional(List<Integer> numbers) {
		numbers.stream()
			.filter(number -> number % 2 == 0)
			.map(number -> number * number)
			.forEach(System.out::println);
		
	}

	private static void printEvenNumbersInListFunctional(List<Integer> numbers) {

		// what to do
		numbers.stream()
//			.filter(FP02Functional::isEven)//using filter 
			.filter(number -> number%2 == 0)//using lambda expression 
			.forEach(System.out::println);//method reference
 	}
	private static void printAllNumbersInListFunctional(List<Integer> numbers) {
		
		// what to do
		numbers.stream()
		.forEach(System.out::println);//method reference
	}

	private static void print(int number) {
		System.out.println(number);
	}

	private static boolean isEven(int number) {
		return number%2 == 0;
	}
}
