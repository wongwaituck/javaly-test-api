package javaly.core;

import org.junit.*;
import java.io.*;
import javaly.model.*;
import static javaly.util.TestUtil.*;

/**
 * Test is an adapter that wraps JUnit and provides a familiar interface
 * for instructors who have previously used JUnit before which functions
 * similarly to the Assert class of JUnit.
 * <p>
 * All Test classes for Javaly that you write should import static all methods from this class.
 * All test cases must be defined within methods and be annotated with the @TestCase tag.
 * Note that the test case method can be of any name should not take in any arguments.
 * <p>
 * You may check the results of your test cases with {@link javaly.core.TestEngine#sampleRun TestEngine.sampleRun()}.
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
 *  &#064;TestCase(expectedOutput = "5")
 *  public void test0() throws Exception{
 *   assertEquals(5, MethodHolder.add(2, 3));
 *  }
 *
 * {@literal @}TestCase(expectedOutput = "6")
 *  public void test1() throws Exception{
 *   assertEquals(6, MethodHolder.add(3, 3));
 *  }
 *
 * {@literal @}TestCase(expectedOutput = "-1")
 *  public void test2() throws Exception{
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
 *  //if you wish to hide the testcase
 * {@literal @}TestCase (expectedOutput = "Expected string to display in case of error", hidden=true)
 *  public void testY() throws Exception{
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
 *  //for exception questions
 * {@literal @}TestCase (expectedOutput = "Expected string to display in case of error")
 *  public void testSysOut() throws Exception{
 *    //preparation code (any throwable)
 *    expectThrowable(new Exception("message here"));
 *    ...
 *    //test code
 *    methodThatThrowsException();
 *    ...
 *  }
 * }
 * </pre>
 * <p>
 * @author      Clarence Ngoh
 * @author      Wong Wai Tuck
 * @version     0.9
 */
public final class Test{

  private static final Runs runs = new Runs();
  private static ByteArrayOutputStream outContent;
  private static PrintStream defaultSystemOut;
  private static boolean hasRetrieved = false;
  private static ExpectedThrowable expected;

  static {
    //save the original first
    defaultSystemOut = System.out;
    outContent = new ByteArrayOutputStream();
    resetThrowable();
  }

  private Test() {
  }

  /**
    * Compares two objects and saves the result to the collated results.
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
      r = new Result(description, o1, o2, true);
    } catch (AssertionError e){
      r = new Result(description, o1, o2, false);
    } catch (Exception e){
      r = new Result(description, o1, String.valueOf(captureStacktrace(e)), false);
    } finally {
      if(hasRetrieved){
        runs.add(new Run(r, ""));
      } else {
        runs.add(new Run(r, retrieveSystemOutput()));
      }
    }
  }

  /**
    * Retrieves system output as a String from the redirected stream.
    * <p>
    * @return A String with the contents of the redirected stdout stream.
    */
  public static String retrieveSystemOutput() {
    hasRetrieved = true;
    return captureSystemOutput(outContent);
  }


  /**
    * Prepares the test to check whether a particular Throwable is thrown by the method to be tested
    * <p>
    * @param e            The exception thrown
    */
  public static void expectThrowable(Throwable e) {
    ExpectedThrowable et = new ExpectedThrowable(e);
    expected = et;
  }

  /**
    * Prepares the test to check whether a particular Throwable is thrown by the method to be tested with a description for this particular test case
    * <p>
    * @param description        Description of the test case
    * @param e            The exception thrown
    */
  public static void expectThrowable(String description, Throwable e) {
    ExpectedThrowable et = new ExpectedThrowable(e, description);
    expected = et;
  }


  /**
    * Retrieves the runs from assert statements.
    * <p>
    * For internal use only.
    * @return A Runs object containing the all the runs of each of the assert statements
    */
  public static Runs getRuns(){
    return runs;
  }


  /**
    * Adds a failed test case (due to a thrown exception) to the test results
    * <p>
    * For internal use only.
    * @param expected     The expected output as a String, to be set at the annotation
    * @param e            The exception thrown
    */
  public static void addExceptionalCase(String expected, Throwable e){
      String sysOut = "";
      if(!hasRetrieved){
        sysOut = retrieveSystemOutput();
      }
      Result r = new Result(expected, captureStacktrace(e), false);
      runs.add(new Run(r, sysOut));
  }


  /**
    * Adds a test case (due to an expected thrown exception) to the test results
    * <p>
    * For internal use only.
    * @param e            The Throwable thrown
    */
  public static void addExceptionRun (Throwable e){
      Result r = expected.matchThrowable(e);

      String sysOut = "";
      if(!hasRetrieved){
        sysOut = retrieveSystemOutput();
      }

      runs.add(new Run(r, sysOut));
      resetThrowable();
  }

  /**
    * Redirects system out a temporary stream that can be retrieved.
    * <p>
    * For internal use only.
    */
  public static void redirectSysOut(){
    PrintStream redirect = new PrintStream(outContent);
    System.setOut(redirect);

  }

  /**
    * Hides the output of the last run by converting it to a HiddenResult from a Resultable
    * <p>
    * For internal use only.
    */
  public static void convertLastRunToHidden(){
    Run run = runs.popRun();
    Resultable result = run.getResult();
    run.setResult(HiddenResult.convertResultToHidden(result));
    runs.add(run);
  }


  /**
    * Returns the sys out to stdout.
    * <p>
    * For internal use only.
    */
  public static void rollbackSysOut() {
    System.setOut(defaultSystemOut);
    hasRetrieved = false;
  }

  /**
    * Gets the current ExpectedThrowable in the Test that is defined by the user-defined testcase or the default expected throwable
    * <p>
    * For internal use only.
    * @return an ExpectedThrowable object which is what the Test expects to be thrown
    */
  public static ExpectedThrowable getExpectedThrowable(){
    return expected;
  }

  /**
    * Resets the expected throwable to the default(none).
    * <p>
    * For internal use only.
    */
  public static void resetThrowable(){
    expected = ExpectedThrowable.EXCEPTION_NONE;
  }

  /**
    * Fails the testcase if expected exception is not thrown.
    * <p>
    * For internal use only.
    */
  public static void failIfExpectedNotThrown(){
    if(!expected.equals(ExpectedThrowable.EXCEPTION_NONE)){
      String sysOut = "";
      if(!hasRetrieved){
        sysOut = retrieveSystemOutput();
      }
      Result r = new Result(expected.getDescription(), expected.getClazz().getName(), "no exception thrown", false);
      runs.add(new Run(r, sysOut));
      resetThrowable();
    }
  }
}
