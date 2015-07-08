var fs = require('fs');
var filepath = './';

var text = '{"testCases":[{"input":"2, 3","output":"\\"5\\""},{"input":"3, 3","output":"6"},{"input":"-4, 3","output":"-1"}],"questionType":"SYSTEM_OUT","methodName":"add"}';

function writeFile (testCaseData) {
  var fileName = 'StagingMethodTest.java';
  var fileContents = 'public class StagingMethodTest extends Test{public static void main(String[] args){ e35089b2d968d2c00562279dd210847f3e156caa7c9affbaa45a25c6c0e75edf }}';
  var insertionHash = 'e35089b2d968d2c00562279dd210847f3e156caa7c9affbaa45a25c6c0e75edf'; //SHA-256 of ILoveJava
  var data = JSON.parse(testCaseData);
  var statements = '';
  var testCase;

  for (testCase in data["testCases"]) {
    //construct statement
    statements += buildStatement(data["testCases"][testCase], data["questionType"], data["methodName"]);
  }

  fileContents = fileContents.replace(insertionHash, statements);

  fs.writeFile(filepath + fileName, fileContents, function(err) {
    if (err) {
      console.error("Could not write file: %s", err);
    }
  });

}

function buildStatement (testCase, questionType, methodName) {
  var statement = '';
  if (questionType === 'RETURN'){
    /*
    Structure:
      assertEquals(output, new Holder().methodName(input));
    */
    statement = 'assertEquals(' +
                testCase["output"] +
                ', new MethodHolder().' +
                methodName +
                '(' +
                testCase["input"] +
                '));';

  } else if (questionType === 'SYSTEM_OUT') {
    /*
    Structure:
      new MethodHolder.methodName(params);
      assertEquals(output, getOutputStream());
    */
    statement = 'new MethodHolder().' +
                methodName +
                '(' +
                testCase["input"] +
                ');' +
                'assertEquals(' +
                testCase["output"] +
                ', getOutputStream());';
  } else {
    //shouldn't happen
  }

  return statement;
}

writeFile(text);
