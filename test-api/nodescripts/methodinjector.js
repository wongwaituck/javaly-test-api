var text = 'public static int add(int a, int b){return (a + b);}';

function generateUserJavaCode (userMethod) {
  var fileName = 'MethodHolder.java';
  var fileContents = 'import java.io.*;import java.util.*; public class MethodHolder{ e35089b2d968d2c00562279dd210847f3e156caa7c9affbaa45a25c6c0e75edf }';
  var insertionHash = 'e35089b2d968d2c00562279dd210847f3e156caa7c9affbaa45a25c6c0e75edf'; //SHA-256 of ILoveJava
  fileContents = fileContents.replace(insertionHash, userMethod);
  return fileContents;
}

console.log(generateUserJavaCode(text));
