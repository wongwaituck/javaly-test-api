import static javaly.core.Test.*;
import javaly.core.*;

public class SystemOutputTest {

    public static void main(String[] args){
        TestEngine.sampleRun(new StagingMethodTest());
    }

    @TestCase(expectedOutput = "5")
    public void test0() {
        MethodHolder.add(2,3);
        assertEquals("5", retrieveSystemOutput());
    }

    @TestCase(expectedOutput = "6")
    public void test1() {
        MethodHolder.add(3,3);
        assertEquals("6", retrieveSystemOutput());
    }

    @TestCase(expectedOutput = "-1")
    public void test2() {
        MethodHolder.add(-4,3);
        assertEquals("-1", retrieveSystemOutput());
    }
}
