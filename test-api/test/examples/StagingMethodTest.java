
import static javaly.core.Test.*;
import javaly.core.*;

public class StagingMethodTest {

    @TestCase(expectedOutput = "5")
    void test0() {
        assertEquals(5, MethodHolder.add(2, 3));
    }

    @TestCase(expectedOutput = "6")
    void test1() {
        assertEquals(6, MethodHolder.add(3, 3));
    }

    @TestCase(expectedOutput = "-1")
    void test2() {
        assertEquals(-1, MethodHolder.add(-4, 3));
    }
}
