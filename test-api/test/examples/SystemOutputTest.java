//SAMPLE
import static javaly.Test.*;

public class SystemOutputTest{

  public static void main(String[] args){
    //prep code
    //...
    enableCaptureSysOut();

    new MethodHolder().sysAdd(1, 2);
    assertEquals("3", getStdOutput());

    new MethodHolder().sysAdd(1, 1);
    assertEquals("2", getStdOutput());

    new MethodHolder().sysAdd(2, 2);
    assertEquals("4", getStdOutput());
    finish();
    printResults();
  }


}
