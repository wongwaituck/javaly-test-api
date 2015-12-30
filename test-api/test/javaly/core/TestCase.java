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
 * If you wish to make your test cases hidden from the user, please include the <code>hidden=true</code> parameter as below
 * By default, hidden will be falss, i.e. the user will be able to see the expected output of the test.
 * Note that the test case method can be of any name should not take in any arguments and the visibility should be <code>public</code>
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
 * public class StagingMethodTest{
 * {@literal @}TestCase(expectedOutput = "5")
 *  public void test0() throws Exception {
 *   assertEquals(5, MethodHolder.add(2, 3));
 *  }
 *
 * {@literal @}TestCase(expectedOutput = "6")
 *  public void test1() throws Exception {
 *   assertEquals(6, MethodHolder.add(3, 3));
 *  }
 *
 * {@literal @}TestCase(expectedOutput = "-1", hidden=true)
 *  public void test2() throws Exception {
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
 * {@literal @}TestCase (expectedOutput = "Expected string to display in case of error")
 *  public void testX() throws Exception{
 *    //preparation code
 *    ...
 *    //test code
 *    assertEquals([description,] expectedObject, outputObject);
 *    ...
 *  }
 *  //for system.out questions
 * {@literal @}TestCase (expectedOutput = "Expected string to display in case of error")
 *  public void testSysOut() throws Exception{
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
 * @version     0.9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
public @interface TestCase {
	public String expectedOutput() default "";
	public boolean hidden() default false;
}
