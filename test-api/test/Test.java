import org.junit.*;
import java.io.*;
import static util.TestUtil.*;

public class Test{

  private static final Results results  = new Results();
  private static ByteArrayOutputStream outContent;


  public static void assertEquals(Object o1, Object o2) {
    assertEquals("", o1, o2);
  }


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


  public static void setOutputStream(ByteArrayOutputStream oc){
    outContent = oc;
  }


  public static String getOutputStream(){
    return captureSystemOutput(outContent);
  }


  public static Results getResults(){
    return results;
  }

}
