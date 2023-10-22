const judgeHome = (req, res) => { 
    res.send("Hello World!")
};

const judgeExec = async (req, res) => {
    //take body from frontend
	//default language would be java	
	const {langugae = 'java', code} = req.body;

	//if user does not code anything and just runs
	if(code == undefined){
		return res.status(400).json({sucess: false, error: "Empty code body"})
	}
    //if user writes something and runs
	try{
		//const filePath = await generateFile(language, code);
		//const output = await executeJava(filePath);
		res.send({ filePath, output });
	}catch (error) {
		res.status(500).json({sucess: false, error: error.message })
	}
};

module.exports={judgeExec, judgeHome};


