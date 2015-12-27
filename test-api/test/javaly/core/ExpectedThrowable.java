package javaly.core;

import javaly.model.*;

public class ExpectedThrowable{
  private Class<?> clazz;
  private String message;
  private String description;

  public static final ExpectedThrowable EXCEPTION_NONE = new ExpectedThrowable("".getClass(), "I AM EMPTY");

  public ExpectedThrowable(Throwable e){
    this(e, "");
  }

  public ExpectedThrowable(Throwable e, String description){
    this(e.getClass(), e.getMessage(), description);
  }

  public ExpectedThrowable(Class<?> clazz){
    this(clazz, null);
  }

  public ExpectedThrowable(Class<?> clazz, String message){
    this(clazz, message, "");
  }

  public ExpectedThrowable(Class<?> clazz, String message, String description){
    this.clazz = clazz;
    this.message = message;
    this.description = description;
  }

  public Result matchThrowable(Throwable e){
    //do not match message if expectedthrowable message is null
    Result r = null;
    StringBuilder sb = new StringBuilder();
    boolean isMatchingThrowable = e.getClass().equals(clazz);
    if(message != null){
      //match both the message and throwable
      boolean isMatchingMessage = false;
      if(e.getMessage() != null){
        isMatchingMessage = e.getMessage().equals(message);
      }

      if(isMatchingMessage && isMatchingThrowable){
        r = new Result(description, clazz.getName(), e.getClass().getName(), true);

      } else{
        sb.append("Exception thrown: ");
        sb.append(e.getClass().getName());
        sb.append("\n");
        sb.append("Message thrown: ");
        sb.append(e.getMessage());

        r = new Result(description, toString(), sb.toString(), false);
      }
    } else {
      //match the throwable class only
      if(isMatchingThrowable){
        r = new Result(description, clazz.getName(), e.getClass().getName(), true);

      } else{
        sb.append("Exception thrown: ");
        sb.append(e.getClass().getName());

        r = new Result(description, toString(), sb.toString(), false);
      }

    }
    return r;
  }


  public String toString(){
    StringBuilder sb = new StringBuilder();

    sb.append("Exception thrown: ");
    sb.append(clazz.getName());
    sb.append("\n");

    sb.append("Message thrown: ");
    sb.append(message);

    return sb.toString();
  }

  public boolean equals(Object a){
    if(a instanceof ExpectedThrowable){
      ExpectedThrowable e = (ExpectedThrowable) a;

      return clazz.equals(e.clazz) &&
        message.equals(e.message) &&
        description.equals(e.description);
    }
    return false;
  }

  public static boolean isNoneException(ExpectedThrowable e){
    return e.equals(EXCEPTION_NONE);

  }
}
