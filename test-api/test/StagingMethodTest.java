import static javaly.core.Test.*;
import javaly.core.*;
public class StagingMethodTest{
	@TestCase(expectedOutput="10")
	public void test0(){
		int[] arr ={1,2,3,4};
		if(arr.length > 2){
			throw new NullPointerException();
		}
		assertEquals("{1,2,3,4}", 10, Adder.add(arr));
	}

	@TestCase(expectedOutput="4")
	public void test1(){
		int[] arr = {1,1,1,1};
		assertEquals("{1,1,1,1}", 4, Adder.add(arr));
	}
}
