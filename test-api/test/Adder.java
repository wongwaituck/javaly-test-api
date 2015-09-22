public class Adder{
	public static int add(int [] arr){
		int sum = 0;
		for(int i : arr){
			sum += i;
		}
		System.out.println("Some debug code");
		return sum;
	}
}
