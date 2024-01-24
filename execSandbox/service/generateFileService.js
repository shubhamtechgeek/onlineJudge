const fs = require('fs');
const path = require('path');
const {v4: uuid} = require('uuid');

const dirCode = path.join(__dirname, 'codes');
if(!fs.existsSync(dirCode)){
	fs.mkdirSync(dirCode, {recursive: true});
}

const generateFile = async (language, code) => {
	const jobID = uuid();                                 //jhvc25j2hcv2j5hv2j5
	const filename = `${jobID}.${language}`;              //jhvc25j2hcv2j5hv2j5.cpp
	const filePath = path.join(dirCode, filename);
	await fs.writeFileSync(filePath, code);
    return filePath;
};

module.exports = {generateFile};