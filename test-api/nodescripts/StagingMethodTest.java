public class StagingMethodTest extends Test{public static void main(String[] args){ new MethodHolder().add(2, 3);assertEquals("5", getOutputStream());new MethodHolder().add(3, 3);assertEquals(6, getOutputStream());new MethodHolder().add(-4, 3);assertEquals(-1, getOutputStream()); }}