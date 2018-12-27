package CyclicGroup;

public class CyclicGroupNumberSet {

	public static void main(String[] args) {
		findCyclicGroup(24);
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
