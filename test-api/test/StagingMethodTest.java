import static javaly.core.Test.*;
import javaly.core.*;
public class StagingMethodTest{
	@TestCase(expectedOutput="10")
	public void test0(){
		expectThrowable(new ExpectedThrowable(new NullPointerException("some message"), "interesting"));
		int[] arr ={1,2,3,4};
		if(arr.length > 2){
			throw new NullPointerException("some message");
		}
		assertEquals("{1,2,3,4}", 10, Adder.add(arr));
	}

	@TestCase(expectedOutput="4", hidden=true)
	public void test1(){
		int[] arr = {1,1,1,1};
		assertEquals("objects are identical", 4, Adder.add(arr));
	}

	@TestCase(expectedOutput="4")
	public void test2(){
		int[] arr = {1,1,1,1};
		assertEquals("objects are identical", 4, Adder.add(arr));
	}
}
