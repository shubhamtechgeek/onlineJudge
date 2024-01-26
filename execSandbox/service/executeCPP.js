const { exec } = require('child_process');
const path = require('path');

const executeCPP = {
  async compile(cppFilePath) {
    console.log("in compile function");
    return new Promise((resolve, reject) => {
      // Compile the C++ code using g++
      const className = path.basename(cppFilePath);
      console.log(className);
      const compiledFilePath = path.join(path.dirname(cppFilePath), className.replace('.cpp', '.exe'));
      console.log(compiledFilePath);
 // Get the correct path to the executable
      const compileCommand = `g++ "${cppFilePath}" -o "${compiledFilePath}"`;
      console.log(compileCommand);
      exec(compileCommand, (compileError, compileStdout, compileStderr) => {
        if (compileError || compileStderr) {
          console.error(`Compilation failed: ${compileError || compileStderr}`);
          reject(`Compilation failed: ${compileError || compileStderr}`);
        } else {
          resolve(compiledFilePath);
        }
      });
    });
  },
  
  async execute(cppFilePath, input) {
    return new Promise((resolve, reject) => {
      console.log("in execution");
      const filePath = path.dirname(cppFilePath);
      const className = path.basename(cppFilePath);
      console.log(cppFilePath);
      const runProcess = exec(`cd ${filePath} && .\\${className}`, (runError, runStdout, runStderr) => {
        if (runError || runStderr) {
          console.error(`Execution failed: ${runError || runStderr}`);
          reject(`Execution failed: ${runError || runStderr}`);
        }else {
          // Replace "\r\n" with "\n" in the output
          const normalizedOutput = runStdout.replace(/\r\n/g, '\n');
          console.log(normalizedOutput);
          resolve(normalizedOutput);
        }
      });
  
      if (input != undefined) {
        runProcess.stdin.write(input);
        runProcess.stdin.end();
      }

      runProcess.on('exit', (code) => {
        if (code !== 0) {
          reject(`C++ program exited with non-zero code: ${code}`);
        }
      });

      setTimeout(() => {
        runProcess.kill(); 
       
        if(input === undefined){
          resolve(`Please Enter a valid input`);
        }
        console.log("TLE");
        resolve(`Time Limit Exceeded (TLE)`);
      }, 1000); 
    });
  },
  
};


module.exports = executeCPP;

