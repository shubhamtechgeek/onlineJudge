const { generateFile } = require("../service/generateFileService");
const  executeCPP  = require("../service/executeCPP");
const executeJAVA = require('../service/executeJAVA');


const judgeHome = (req, res) => {
  res.send("Hello World!");
};

const judgeExec = async (req, res) => {
  //take body from frontend
  //default language would be java
  const { language, code, input } = req.body;
  console.log("1");
  //if user does not code anything and just runs
  if (code == undefined) {
    return res.status(400).json({ sucess: false, error: "Empty code body" });
  }
  //if user writes something and runs
  try {
    const filePath = await generateFile(language, code);
    console.log("2");
    let executeFunction;
    if (language === "java") executeFunction = executeJAVA;
    else if (language === "cpp") executeFunction = executeCPP;
    let compiledFilePath
      try {
        console.log("3");
        compiledFilePath = await executeFunction.compile(filePath);
        console.log("compiling done");
      }catch (compileError) {
        errResp = { message: compileError, statusCode: 400 };
        res.status(400).json({ sucess: false, error: JSON.stringify(errResp) });
        return;
      }
      const result = await executeFunction.execute(compiledFilePath, input);
      console.log("execution done");
      res.status(200).json({ success: true, data: result });
    }catch(error){
      errResp = { message: error, statusCode: 400 };
      res.status(400).json({ sucess: false, error: JSON.stringify(errResp) });
  
    };
  }

module.exports = { judgeExec, judgeHome };
