package javaly.core;

import org.junit.*;
import java.io.*;
import javaly.model.*;
import static javaly.util.TestUtil.*;

/**
 * Test is an adapter that wraps JUnit and provides a familiar interface
 * for instructors who have previously used JUnit before.
 * <p>
 * It stores all test results for each execution of assertion statements
 * so that it may be retrieved by our own engine later.
 * <p>
 * However, a key distinction is the fact that assertion failures and other exceptions
 * do not float to the client. Hence, we have provided a
 * <code>printResults()</code> method to check for any errors before
 * submission to Javaly.
 * <p>
 * All Test classes for Javaly that you write must import static all methods from this class.
 * <p>
 * Usage is as below:
 * <p>
 * Let's say your question expects a student to define the following function
 * <p>
 * <code> public static int add(int a, int b){return a + b;} </code>
 * <p>
 * You would have the following Test class:
 * <pre>
 * {@code
 * import static javaly.core.Test.*;
 * public class AddTest{
 *  public static void main(String[] args){
 *    assertEquals(5, [nameOfClass].add(2, 3));
 *    assertEquals(0, [nameOfClass].add(0, 0));
 *    assertEquals(-1, [nameOfClass].add(-1, 0));
 *    finish();
 *    printResults(); //check whether the test cases are correct before submission to javaly
 *  }
 * }
 * }
 * </pre>
 * <p>
 * Or more generally:
 * <pre>
 * {@code
 * import static javaly.core.Test.*;
 * //other import statements
 * ...
 *
 * public class GenericTest{
 *  public static void main(String[] args){
 *  //preparation code
 *   ...
 *  //test code
 *  assertEquals([description,] expectedObject, outputObject);
 *  ...
 *  //print results for checking
 *  printResults();
 *  }
 * }
 * }
 * </pre>
 * <p>
 * If you need to check results from <code>stdout</code>, see {@link javaly.core.SysOutInvoker SysOutInvoker}.
 * @author      Clarence Ngoh
 * @author      Wong Wai Tuck
 * @version     1.0
 */
public final class Test{

  private static final Results results  = new Results();
  private static ByteArrayOutputStream outContent;
  private static PrintStream defaultSystemOut;
  private static boolean IS_SERVER_VERSION = false;

  static {
    //save the original first
    defaultSystemOut = System.out;
    outContent = new ByteArrayOutputStream();
  }

  private Test() {
  }

  /**
    * Compares two objects and saves the result to the results storage.
    * <p>
    * The objects to be compared must already have the <code>equals()</code> method defined.
    * @param o1        Expected object to be created
    * @param o2        Actual object created
    */
  public static void assertEquals(Object o1, Object o2) {
    assertEquals("", o1, o2);
  }

  /**
    * Compares two objects and saves the result together with the description to the results storage.
    * <p>
    * The objects to be compared must already have the <code>equals()</code> method defined.
    * @param description        Description of the test case
    * @param o1                 Expected object to be created
    * @param o2                 Actual object created
    */
  public static void assertEquals(String description, Object o1, Object o2) {
    Result r = null;
    try {
      Assert.assertEquals(o1, o2);
      r = new Result(description, String.valueOf(o1), String.valueOf(o2), true);
    } catch (AssertionError e){
      r = new Result(description, String.valueOf(o1), String.valueOf(o2), false);
    } catch (Exception e){
      r = new Result(description, String.valueOf(o1), String.valueOf(captureStacktrace(e)), false);
    } finally {
      results.add(r);
    }
  }

  /**
    * Prints the results of the assert statements to the standard output.
    * This is to allow inspection to ensure the Test class works.
    * <p>
    * The objects to be printed must already have the <code>toString()</code> method defined.
    */
  public static void printResults(){
    if(!IS_SERVER_VERSION){
      System.out.println(results);
    } else{
      //does nothing in production since it'll mess with the JSON
    }
  }


  /**
    * Retrieves the results assert statements.
    * <p>
    * For internal use only.
    * @return A Results object containing the results of the assert statements
    */
  public static Results getResults(){
    return results;
  }

  /**
    * Sets whether the Test is running on a server.
    * <p>
    * For internal use only.
    * @param isServer     A boolean flag to be set.
    */
  public static void setServerVersion(boolean isServer){
    IS_SERVER_VERSION = isServer;
  }

  /**
    * Adds a failed test case (due to a thrown exception) to the test results
    * <p>
    * For internal use only.
    * @param expected     The expected output as a String, to be set at the annotation
    * @param e            The exception thrown
    */
  public static void addExceptionalCase(String expected, Throwable e){
      results.add(new Result(expected, captureStacktrace(e), false));
  }

  public static void redirectSysOut(){
    PrintStream redirect = new PrintStream(outContent);
    System.setOut(redirect);
  }

  public static String retrieveSystemOutput() {
    return captureSystemOutput(outContent);
  }

  public static void rollbackSysOut() {
    System.setOut(defaultSystemOut);
  }
}
