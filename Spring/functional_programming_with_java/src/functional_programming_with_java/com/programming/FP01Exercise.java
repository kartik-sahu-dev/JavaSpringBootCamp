package functional_programming_with_java.com.programming;

import java.util.List;

public class FP01Exercise {

	public static void main(String[] args) {

//		Exercise 1 - print only odd no.
		List<Integer> numbers = List.of(12, 34, 23, 63, 25);
		printOddNumber(numbers);

		List<String> courses = List.of("Spring","Spring boot","API","AWS","Azure");
		
//		Exercise 2 - print all courses individually
		printAllCourse(courses);
		 
//		Exercise 3 - print courses containing word "Spring"
		printCourseHavingSpring(courses);
		
//		Exercise 4 - print courses whose name atleast 4 letters
		courseHavingAtleat4Letters(courses);

//		Exercise 5 - print cubes of of odd numbers
		printOddNumberCubes(numbers);

//		Exercise 6 - print the no. of characters in each course
		printAllCoursesCharacterNumber(courses);
		
	}

	private static void printAllCoursesCharacterNumber(List<String> courses) {
		courses.stream()
			.map(course -> course + " " + course.length())
			.forEach(System.out::println);
		
	}

	private static void printOddNumberCubes(List<Integer> numbers) {
		numbers.stream()
		.filter(number -> number % 2 != 0)
		.map(number -> number * number * number)
		.forEach(System.out::println);

		
	}

	private static void printCourseHavingSpring(List<String> courses) {
		 courses.stream()
		 	.filter(course -> course.contains("Spring"))
		 	.forEach(System.out::println);
	}

	private static void courseHavingAtleat4Letters(List<String> courses) {
		 courses.stream()
		 	.filter(course -> course.length() >= 4)
		 	.forEach(System.out::println);
	}

	private static void printAllCourse(List<String> courses) {
		courses.stream()
			.forEach(System.out::println);
	}

	private static void printOddNumber(List<Integer> numbers) {
		 numbers.stream()
		 	.filter(number -> number%2 != 0)
		 	.forEach(System.out::println);
	}
 }
