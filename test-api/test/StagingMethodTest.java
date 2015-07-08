//dynamically generated
public class StagingMethodTest extends Test{

  public static void main(String[] args){
    //prep code
    //test 1
    /*
    Structure:
      assertEquals(expected, new Holder().methodName(params));
    */
    //TODO: Insert HASH

    new MethodHolder().add(1, 2);
    assertEquals("3", getOutputStream());

    //test 2
    //...
    new MethodHolder().add(2, 2);
    assertEquals("2", getOutputStream());

    //test 3
    new MethodHolder().add(3, 2);
    assertEquals("4", getOutputStream());
  }


}
