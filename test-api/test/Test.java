import org.junit.*;
import java.io.*;
import static util.TestUtil.*;

public class Test{
  
  private static final Results results  = new Results();
  private static ByteArrayOutputStream outContent;

  public static void setOutputStream(ByteArrayOutputStream oc){
    outContent = oc;
  }

  public static String getOutputStream(){
    return captureSystemOutput(outContent);
  }

  public static void assertEquals(Object o1, Object o2) {
    try {
      Assert.assertEquals(o1, o2);
      results.add(new Result(String.valueOf(o1), String.valueOf(o2), true));
    } catch (AssertionError e){
      results.add(new Result(String.valueOf(o1), String.valueOf(o2), false));
    } catch (Exception e){
      addResult(o1, captureStacktrace(e), false);
    }

  }


  public static void assertEquals(String description, Object o1, Object o2) {
    try {
      Assert.assertEquals(o1, o2);
      results.add(new Result(description, String.valueOf(o1), String.valueOf(o2), true));
    } catch (AssertionError e){
      results.add(new Result(description, String.valueOf(o1), String.valueOf(o2), false));
    } catch (Exception e){
      addResult(description, o1, captureStacktrace(e), false);
    }
  }

  public static Results getResults(){
    return results;
  }

  private static void addResult(Object expectedOutput, Object actualOutput, boolean success) {
      results.add(new Result(String.valueOf(expectedOutput), String.valueOf(actualOutput), success));
  }


  private static void addResult(String description, Object expectedOutput, Object actualOutput, boolean success) {
      results.add(new Result(description, String.valueOf(expectedOutput), String.valueOf(actualOutput), success));
  }


}
