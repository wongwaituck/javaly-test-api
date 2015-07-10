package javaly.util;

import java.io.*;

public class TestUtil {
    private static final String ERROR_EXP = "Caused by:";

    public static String captureSystemOutput(ByteArrayOutputStream outContent) {
        String actualOutput = outContent.toString();
        outContent.reset(); //clearing the ByteArrayOutputStream, or future calls will show duplicated data
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

    public static String removeTrailingNewLine(String s) {
        int length = s.length();
        char c = s.charAt(length-1);
        if (c == '\n') {
            return s.substring(0, length-1);
        } else {
            return s;
        }
    }

}
