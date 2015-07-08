# javaly-test-api
Test API for Javaly

Usage: 
Lifecycle:
1. User code is injected into MethodHolder.java (TODO)
2. Test code is injected into StagingMethodTest.java with testwriter.js

Compilation 
`javac -cp ./;libs/*; -d classes TestEngine.java`

Execution
`java -cp ./;libs/*;classes; TestEngine`

testwriter.js usage 
call the function `writeFile(inputJSONAsText)`

Expected JSON for testwriter.js
For return type questions
`{
  "testcases": [
    {
      "input": "2, 3",
      "output": "5"
    },
    {
      "input": "3, 3",
      "output": "6"
    },
    {
      "input": "-4, 3",
      "output": "-1"
    }
  ],
  "questionType": "RETURN",
  "methodName": "add"
}`

For sysout type questions
`{
  "testcases": [
    {
      "input": "2, 3",
      "output": "\"5\""
    },
    {
      "input": "3, 3",
      "output": "\"6\""
    },
    {
      "input": "-4, 3",
      "output": "\"-1\""
    }
  ],
  "questionType": "SYSTEM_OUT",
  "methodName": "add"
}`

#Caveat Emptor:
- Strings NEED to be escaped! On a per parameter basis!
- For System.out questions, answers have to be stored as Strings and MUST be escaped for the output!

