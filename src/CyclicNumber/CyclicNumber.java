package CyclicNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CyclicNumber {

			/*
			142857 × 1 = 142857
			142857 × 2 = 285714
			142857 × 3 = 428571
			142857 × 4 = 571428
			142857 × 5 = 714285
			142857 × 6 = 857142
			Input Data:
			Input a number: 142857 */
	// refer: https://www.w3resource.com/java-exercises/numbers/java-number-exercise-19.php
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number to check Cyclic number");
		
		Long n = Long.valueOf(scanner.nextLong());
		List<Long> listOfCyclic = new ArrayList<>();
		
		for (Long i = Long.valueOf(1); i < n; i++) {
			checkCyclicNumber(i, listOfCyclic);
		}
		
		if(listOfCyclic.size() > 0) {
		
			for (Long item : listOfCyclic) {
			System.out.println(item);
		
			}
		}
		
	}
	
	public static void checkCyclicNumber(Long n, List<Long> listOfCyclic) {
		int length = checkLengh(n) + 1;
		StringBuffer stringBuffer = new StringBuffer();
		
		for (int i = 0; i < length - 1; i++) {
			stringBuffer.append('9');
		}
		
		Integer total = Integer.valueOf(stringBuffer.toString());
		System.out.println("calculating!!");
		if(n*length == total) {
			listOfCyclic.add(n);
			System.out.println("ok");
		}
		
		
	}
	
	
	
	public static int checkLengh(long n) {
		int i = 1;
		while (n >= 10) {
			n = n/10;
			i += 1;
		}
		return i;
	}

}
