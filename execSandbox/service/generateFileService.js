const fs = require('fs');
const path = require('path');
const {v4: uuid} = require('uuid');

const tempDir = path.join(__dirname, 'temp');
    fs.mkdirSync(tempDir, { recursive: true });

const generateFile = async (language, code) => {
	const userCodeFilePath = path.join(tempDir, 'submission.' + getExtensionForLanguage(language));
    fs.writeFileSync(userCodeFilePath, code);
    return userCodeFilePath;
};


const getExtensionForLanguage = (language) => {
    switch (language.toLowerCase()) {

      case 'cpp':
        return 'cpp';
      case 'java':
        return 'java';
      default:
        throw new ErrorResponse(`Unsupported language: ${language}`, 400);
    }
}

module.exports = {generateFile};