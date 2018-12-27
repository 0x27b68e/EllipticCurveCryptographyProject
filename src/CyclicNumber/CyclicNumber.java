package CyclicNumber;

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
		long n = scanner.nextLong();
		
		
		int length = checkLengh(n) +1;
		StringBuffer stringBuffer = new StringBuffer();
		
		for (int i = 0; i < length - 1; i++) {
			stringBuffer.append('9');
		}
		Integer total = Integer.valueOf(stringBuffer.toString());
		
		if(n*length == total) {
			System.out.println("Ok");
		} else {
			System.out.println("Not");
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
