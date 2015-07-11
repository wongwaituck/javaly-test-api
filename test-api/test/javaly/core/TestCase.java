package javaly.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

/**
 * {@literal @}TestCase is an annotation that tells our test engine which methods are test cases.
 * This is to allow our test engine to catch any exceptions that may occur when the user runs the code.
 * All test cases must be defined within methods and be annotated with the @TestCase tag.
 * Note that the test case method can be of any name should not take in any arguments.
 * <p>
 * Usage is as below:
 * <p>
 * Let's say your question expects a student to define the following function
 * <p>
 * <code> public static int add(int a, int b){return a + b;} </code>
 * <p>
 * You would have the following Test class:
 * <pre>
 * import static javaly.core.Test.*;
 * import javaly.core.*;
 * public class AddTest{
 *	{@literal @}TestCase(expectedOutput = "5")
 *  void test0() {
 *   assertEquals(5, MethodHolder.add(2, 3));
 *  }
 *
 *	{@literal @}TestCase(expectedOutput = "6")
 *  void test1() {
 *   assertEquals(6, MethodHolder.add(3, 3));
 *  }
 *
 *	{@literal @}TestCase(expectedOutput = "-1")
 *  void test2() {
 *    assertEquals(-1, MethodHolder.add(-4, 3));
 *  }
 *
 * }
 * </pre>
 * <p>
 * Or more generally:
 * <pre>
 * import static javaly.core.Test.*;
 * import javaly.core.*;
 * //other import statements
 * ...
 *
 * public class GenericTest{
 *	{@literal @}TestCase (expectedOutput = "Expected string to display in case of error")
 *  void testX(){
 *    //preparation code
 *    ...
 *    //test code
 *    assertEquals([description,] expectedObject, outputObject);
 *    ...
 *  }
 *  //for system.out questions
 *	{@literal @}TestCase (expectedOutput = "Expected string to display in case of error")
 *  void testSysOut(){
 *    //preparation code
 *    ...
 *    //test code
 *    methodThatPrintsToSysOut();
 *    assertEquals([description,] expectedObject, retrieveSystemOutput());
 *    ...
 *  }
 * }
 * </pre>
 * <p>
 * @author      Wong Wai Tuck
 * @version     0.7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
public @interface TestCase {

	public String expectedOutput() default "UNDEFINED, PLEASE CONTACT QUESTION SETTER";
}
