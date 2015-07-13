import com.google.gson.*;
import java.io.*;
import javaly.model.*;
import javaly.core.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestEngine {

    public static void main(String[] args) {
        //TODO: Think about how to integrate arbitrary Test Files (can standardize name of test files?)
        //run the test code
        //THIS FILE IS TO BE HOSTED ON THE SERVER
        Class<?> clazz = StagingMethodTest.class;

        // Process @TestCase
      	for (Method method : clazz.getDeclaredMethods()) {
      		// if method is annotated with @TestCase
      		if (method.isAnnotationPresent(TestCase.class)) {
      			Annotation annotation = method.getAnnotation(TestCase.class);
      			TestCase test = (TestCase) annotation;

            //redirect stdout to our own
            Test.redirectSysOut();

            try {
              method.invoke(clazz.newInstance());
            } catch (Throwable e) {
              Test.addExceptionalCase(test.expectedOutput(), e);
            }
            Test.rollbackSysOut();
          }

      	}

        //get the results
        Results r = Test.getResults();

        //Output the result as JSON to System.out
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(r));
    }

}
