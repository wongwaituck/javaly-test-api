var fs = require('fs');
var filepath = './';

var text = {"testCases":[{"input":"2, 3","output":"\"5\""},{"input":"3, 3","output":"\"6\""},{"input":"-4, 3","output":"\"-1\""}],"questionType":"SYSTEM_OUT","methodName":"add","static":true};

function writeFile (testCaseData) {
  var fileName = 'StagingMethodTest.java';
  var fileContents = 'import static javaly.core.Test.*;import javaly.core.*;public class StagingMethodTest{ e35089b2d968d2c00562279dd210847f3e156caa7c9affbaa45a25c6c0e75edf }';
  var insertionHash = 'e35089b2d968d2c00562279dd210847f3e156caa7c9affbaa45a25c6c0e75edf'; //SHA-256 of ILoveJava
  var data = testCaseData;
  var statements = '';
  var testCase;
  var count = 0;

  for (testCase in data["testCases"]) {
    //construct statement
    statements += buildStatement(data["testCases"][testCase], data["questionType"], data["methodName"], data["static"], count);
    count++;
  }

  fileContents = fileContents.replace(insertionHash, statements);

  fs.writeFile(filepath + fileName, fileContents, function(err) {
    if (err) {
      console.error("Could not write file: %s", err);
    }
  });

}

function buildStatement (testCase, questionType, methodName, isStatic, count) {
  var statement = '';
  var methodInvoker = '';

  if (isStatic){
    methodInvoker = 'MethodHolder.';
  } else {
    methodInvoker = 'new MethodHolder().';
  }

  if (questionType === 'RETURN'){
    /*
    Structure:
      @TestCase (expectedOutput = "X")
      void test<count>(){
        assertEquals(output, new MethodHolder().methodName(input));
      }
    */
    statement = '@TestCase (expectedOutput = \"' +
                testCase["output"] +
                '\")' +
                'void test' +
                count +
                '(){' +
                'assertEquals(' +
                testCase["output"] +
                ', ' +
                methodInvoker +
                methodName +
                '(' +
                testCase["input"] +
                '));' +
                '}';

  } else if (questionType === 'SYSTEM_OUT') {
    /*
    Structure:
      @TestCase (expectedOutput = "X")
      void test<count>(){
        new MethodHolder.methodName(params);
        assertEquals(output, retrieveSystemOutput());
      }
    */
    statement = '@TestCase (expectedOutput = ' +
                testCase["output"] +
                ')' +
                'void test' +
                count +
                '(){' +
                methodInvoker +
                methodName +
                '(' +
                testCase["input"] +
                ');' +
                'assertEquals(' +
                testCase["output"] +
                ', retrieveSystemOutput());' +
                '}';
  } else {
    //shouldn't happen
  }

  return statement;
}

writeFile(text);
