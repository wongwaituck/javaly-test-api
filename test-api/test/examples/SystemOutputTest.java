//SAMPLE
import static javaly.core.Test.*;
import javaly.core.TestCase;

public class SystemOutputTest{

  public static void main(String[] args){
    redirectSysOut();

    test1();
    test2();
    test3();

    rollbackSysOut();

    printResults();
  }

  //sample sys out case
  @TestCase (expectedOutput = "3")
  static void test1() {
    MethodHolder.sysAdd(1,2);
    assertEquals("3", retrieveSystemOutput());
  }

  //sample returnable case
  @TestCase (expectedOutput = "3")
  static void test2() {
    assertEquals(3, new MethodHolder.add(1, 2));
  }


  @TestCase (expectedOutput = "0")
  static void test2() {
    MethodHolder.sysAdd(0,0);
    assertEquals("0", retrieveSystemOutput());
  }

  @TestCase (expectedOutput = "-1")
  static void test3() {
    MethodHolder.sysAdd(1,-2);
    assertEquals("-1", retrieveSystemOutput());
  }


}
