package CyclicNumber;

import java.math.BigInteger;
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
		String string = scanner.nextLine().trim();
		BigInteger n = BigInteger.valueOf(Long.valueOf(string));
		List<BigInteger> listOfCyclic = new ArrayList<BigInteger>();
		
		for (Long i = Long.valueOf(1); i < n.longValue(); i++) {
			checkCyclicNumber(new BigInteger(String.valueOf(i)), listOfCyclic);
		}
		
		if(listOfCyclic.size() > 0) {
			for (BigInteger item : listOfCyclic) {
			System.out.println(item.intValue());
		
			}
		}
		
	}
	
	public static void checkCyclicNumber(BigInteger n, List<BigInteger> listOfCyclic) {
		int length = checkLengh(n) + 1;
		BigInteger bigIntegerLength = new BigInteger(String.valueOf(length));
		StringBuffer stringBuffer = new StringBuffer();
		
		for (int i = 0; i < length - 1; i++) {
			stringBuffer.append('9');
		}
		
		BigInteger total = new BigInteger(stringBuffer.toString());
		System.out.println("calculating!!");
		if(n.multiply(bigIntegerLength).equals(total)) {
			listOfCyclic.add(n);
			System.out.println("ok");
		}
		
	}
	public static int checkLengh(BigInteger n) {
		int i = 1;
		BigInteger ten = new BigInteger("10");
		while (n.longValue() >= 10) {
			n = n.divide(ten);
			i += 1;
		}
		return i;
	}

}
