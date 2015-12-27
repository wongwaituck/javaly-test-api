package javaly.model;

public class Run{
  private Resultable result;
  private String systemOutput;

  public Run(Resultable result, String systemOutput){
    this.result = result;
    this.systemOutput = systemOutput;
  }

  public Resultable getResult(){
    return result;
  }

  public void setResult(Resultable result){
    this.result = result;
  }

  public String getSystemOutput(){
    return systemOutput;
  }

}
