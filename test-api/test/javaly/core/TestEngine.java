package javaly.core;

import com.google.gson.*;
import java.io.*;
import javaly.model.*;
import javaly.core.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestEngine {

    public static void sampleRun(Object obj) {
        Class<?> clazz = obj.getClass();

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
