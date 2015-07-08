import com.google.gson.*;
import java.io.*;

/**
 * This is a TestEngine variant used to capture stdout for testing
 */
public class TestEngine {
    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public static void main(String[] args) throws Exception {
        //save the original first
        PrintStream sysOut = System.out;

        //redirect stdout and stderr
        System.setOut(new PrintStream(outContent));

        //TODO: Create a factory to control which testing to use

        //Sets the output stream to our stream for the test
        StagingMethodTest.setOutputStream(outContent);

        //run the test code
        StagingMethodTest.main(null);

        //get the results
        Results r = StagingMethodTest.getResults();

        //switching back
        System.setOut(sysOut);

        //Output the result as JSON to System.out
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(r));
    }

}
