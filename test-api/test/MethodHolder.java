public class MethodHolder{
  //inject method here
  public static int add(int a, int b){
    return a + b;
  }

  public static void sysAdd(int a, int b){
    if (a == a)
      throw new RuntimeException();
    System.out.print(a + b);
  }
}
