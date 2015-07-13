
import static javaly.core.Test.*;
import javaly.core.*;

public class StagingMethodTest {

    public static void main(String[] args){
        TestEngine.sampleRun(new StagingMethodTest());
    }

    @TestCase(expectedOutput = "5")
    public void test0() {
        assertEquals(5, MethodHolder.add(2,3));
    }

    @TestCase(expectedOutput = "6")
    public void test1() {
        assertEquals(6, MethodHolder.add(3,3));
    }

    @TestCase(expectedOutput = "-1")
    public void test2() {
        assertEquals(-1, MethodHolder.add(-4,3));
    }
}
