package functional_programming_with_java.com.programming;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PlayingWithOptional {
	public static void main(String[] args) {
		List<String> fruits = List.of("mango","banana","apple");
		
		Predicate<? super String> predicate = fruit -> fruit.startsWith("a");
		Optional<String> optional = fruits.stream().filter(predicate).findFirst();
		
		System.out.println(optional);
		System.out.println(optional.isEmpty());
		System.out.println(optional.get());
	}
}
