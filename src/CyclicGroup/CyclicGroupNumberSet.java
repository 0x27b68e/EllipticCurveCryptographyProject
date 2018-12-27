package CyclicGroup;

import java.util.Scanner;

//EX: input = 6
//We have: <1> = {1, 2, 3, 4, 5, 0}
//         <2> = {2, 4,0}
//         <3> = {3, 0}
//         <4> = {4, 2, 0}
//  	   <5> = {5, 4, 3, 2, 1, 0}  5 + 5 = 10 % 6 = 4, 4 + 5 = 9%6 = 3, 3 + 5 = 8%6 =2, 2 + 5 =7%6 = 1, 1 + 5 = 6%6 =0, 0 + 5 = 5%6 = 5.
// so generator 1 and generator 5 is ok for input 6
// refer this video for easing understanding : https://www.youtube.com/watch?v=hp7bpkNL790

public class CyclicGroupNumberSet {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number to find the Cyclic Group: ");
		int n = scanner.nextInt();
		findCyclicGroup(n);
	}
	
	public static int modulo(int a, int b) {
		if(a ==  0) {
			return b;
		}
		return modulo(b%a, a);
	}
	public static void findCyclicGroup(int n) {
		
		System.out.print("1 ");
		for (int i = 2; i < n; i++) {
			if(modulo(i, n) == 1) {
				System.out.print(i + " ");
			}
		}
	}

}
