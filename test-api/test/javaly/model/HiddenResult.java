package javaly.model;

public class HiddenResult implements Resultable{
  private boolean success;

  public HiddenResult(boolean success){
    this.success = success;
  }

  public boolean isSuccess(){
    return success;
  }

  public static HiddenResult convertResultToHidden(Resultable a){
    return new HiddenResult(a.isSuccess());

  }

}
