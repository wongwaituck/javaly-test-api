//these are the imports you need, add more if your code requires it!
import static javaly.core.Test.*;
import javaly.core.*;

//do not change the class name!
public class StagingMethodTest{

	// each test case is contained within a method as below!
	// the method is annotated with a @TestCase
	@TestCase(expectedOutput="4")
	public void test0(){
		// for now all method questions are held within a class called MethodHolder!
		// if you wish to test a method or multiple methods within a class, call it as necessary and check the output with assertEquals
		// assertEquals takes in a description, expected input (of any type), and expected output
		// i.e. assertEquals(String description, Object input, Object output)
		// please reproduce the expectedoutput as a String parameter for the annotation as above!
		int[] arr = {1,1,1,1};
		assertEquals("{1,1,1,1}", 4, MethodHolder.add(arr));
	}

	//you may hide test cases by passing a hidden=true flag as below!
	@TestCase(expectedOutput="4", hidden=true)
	public void test1(){
		int[] arr = {1,1,1,1};
		assertEquals("{1,1,1,1}", 4, Adder.add(arr));
	}

	// you can catch exceptions using the expectThrowable method! it takes in any Throwable object
	// i.e. expectThrowable([String description,]throwable)
	// if no message is provided (i.e. null), it will not check for the message
	@TestCase(expectedOutput="NullPointerException")
	public void test2(){
		//expectThrowable MUST come before all other code within the method
		expectThrowable("code should throw NullPointerException with message \"this is a test!\"", new NullPointerException("this is a test!"));
		int[] arr = {1,1,1,1};
		if(arr.length > 2){
			throw new NullPointerException("this is a test!");
		}
		assertEquals("{1,1,1,1}", 4, Adder.add(arr));
	}

	// if you wish to check against the system output after a method call, you may use the retreiveSystemOutput() method!
	// note that the return type would be a String
	@TestCase(expectedOutput="4")
	public void test3(){
		int[] arr = {1,1,1,1};
		Adder.add(arr);
		assertEquals("{1,1,1,1}", "4", retrieveSystemOutput());
	}

	public static void main(String[] args){
		TestEngine.sampleRun(new StagingMethodTest());
	}
}
