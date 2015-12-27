package javaly.util;

import java.io.*;

public class TestUtil {
    private static final String ERROR_EXP = "Caused by:";
    private static final String NO_SYS_OUT_ERROR = "";

    public static String captureSystemOutput(ByteArrayOutputStream outContent) {
        String actualOutput = outContent.toString();
        outContent.reset(); //clearing the ByteArrayOutputStream, or future calls will show duplicated data
        if(actualOutput.length() == 0){
          actualOutput = NO_SYS_OUT_ERROR;
        }
        return removeTrailingNewLine(actualOutput);
    }

    public static String captureStacktrace(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String stacktrace = sw.toString();
        //strip the trace from Tester classes onward
        int index = stacktrace.indexOf(ERROR_EXP);
        return stacktrace.substring(index + ERROR_EXP.length(), stacktrace.length()).trim();
    }

    public static String removeTrailingNewLine(String s){
      if(s.length() > 1 && s.substring(s.length() - 2, s.length()).equals("\r\n")){
        return s.substring(0, s.length() - 2);
      } else if (s.length() > 0 && s.substring(s.length() - 1, s.length()).equals("\n")){
        return s.substring(0, s.length() - 1);
      } else {
        return s;
      }
    }
}
