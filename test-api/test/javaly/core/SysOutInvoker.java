package javaly.core;

import java.lang.reflect.Method;
import java.io.*;
import javaly.util.TestUtil;

/**
 * SysOutInvoker allows you to invoke methods that print to the standard output while also
 * catching runtime exceptions that may occur during the execution.
 * <p>
 * It requires you to specify the class, the method and the parameters you wish to pass to the method.
 * All output from this class is meant to be directly passed to the {@link javaly.core.Test#assertEquals(Object, Object) assertEquals} method.
 * <p>
 * Usage is as below:
 * <p>
 * Let's say your question expects a student to define the following function
 * <p>
 * <code> public class Adder {public static void add(int a, int b){System.out.println(a + b);} }</code>
 * <p>
 * You would have the following Test class:
 * <pre>
 * {@code
 * import static javaly.core.Test.*;
 * import static javaly.core.SysOutInvoker.*;
 * public class AddTest{
 *  public static void main(String[] args){
 *    assertEquals("3", invokeAndGetSysOut(Adder.class, "add", 1, 2));
 *    assertEquals("0", invokeAndGetSysOut(Adder.class, "add", 0, 0));
 *    assertEquals("-1", invokeAndGetSysOut(Adder.class, "add", 1, -2));
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
 * import static javaly.core.SysOutInvoker.*;
 * //other import statements
 * ...
 *
 * public class AddTest{
 *  public static void main(String[] args){
 *    //prep code
 *    ...
 *    assertEquals(expectedString, invokeAndGetSysOut([nameOfClassToTest].class, nameOfMethodToTest, param1, param2, ...));
 *    ...
 *    printResults(); //check whether the test cases are correct before submission to javaly
 *  }
 * }
 * }
 * </pre>
 * <p>
 * @author      Wong Wai Tuck
 * @version     1.0
 */

public class SysOutInvoker {

  private SysOutInvoker(){
  }


  /**
    * Invokes the given method of the given class and returns the String containing the contents of the standard output.
    * @param className      Name of the user written class to be tested with its package hierarchy.
    * @param methodName     Name of method that needs to be invoked (that prints to standard output).
    * @param params         Parameters that are used by the method to be invoked.
    * @return A String containing the result of the execution.
    */
  public static String invokeAndGetSysOut(String className, String methodName, Object... params) {
    try{
      Class<?> classToTest = Class.forName(className); //"com.bla.TestActivity"
      return invokeAndGetSysOut(classToTest, methodName, params);
    } catch (Exception e) {
      return TestUtil.captureStacktrace(e);
    }
  }


  /**
    * Invokes the given method of the given class and returns the String containing the contents of the standard output.
    * @param clazz          A Class object of the user written class to be tested
    * @param methodName     Name of method that needs to be invoked (that prints to standard output).
    * @param params         Parameters that are used by the method to be invoked.
    * @return A String containing the result of the execution.
    */
  public static String invokeAndGetSysOut(Class<?> clazz, String methodName, Object... params) {
    //redirect stdout to our own
    PrintStream defaultSystemOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();
    PrintStream redirect = new PrintStream(outcontent);
    System.setOut(redirect);

    try {
      for (Method method : clazz.getDeclaredMethods()) {
        if(method.getName().equals(methodName)){
          method.invoke(clazz.newInstance(), params);
          break;
        }
      }
    } catch (Throwable e) {
      System.setOut(defaultSystemOut);
      return TestUtil.captureStacktrace(e);

    }

    System.setOut(defaultSystemOut);
    return TestUtil.captureSystemOutput(outcontent);
  }
}
