package javaly.model;

public class Run{
  private Result result;
  private String systemOutput;

  public Run(Result result, String systemOutput){
    this.result = result;
    this.systemOutput = systemOutput;
  }

  public Result getResult(){
    return result;
  }

  public String getSystemOutput(){
    return systemOutput;
  }

  public String toString(){
      StringBuilder sb = new StringBuilder();
      sb.append("Description: ");
      sb.append(result.getDescription());
      sb.append("\nExpected: ");
      sb.append(result.getExpected());
      sb.append("\nActual: ");
      sb.append(result.getActual());
      sb.append("\nSuccess: ");
      sb.append(result.isSuccess());
      sb.append("\nSystemOutput: ");
      sb.append(systemOutput);
      sb.append("\n");
      return sb.toString();
  }
}
