import axios from "axios";
import React, { useState } from "react";
import MonacoEditor from "react-monaco-editor";
import CircularProgress from "@material-ui/core/CircularProgress";
import PlayCircleOutlineIcon from "@material-ui/icons/PlayCircleOutline";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPaperPlane } from "@fortawesome/free-solid-svg-icons";

const CodeEditor = ({
  code,
  onChange,
  language,
  submit,
  output,
  runLoading,
  submitLoading,
  handleLanguageSelect,
}) => {
  const editorDidMount = (editor, monaco) => {
    console.log("Editor did mount:", editor);
    // You can perform additional setup here
  };

  const languageList = ["c", "cpp", "java", "python"];

  const editorOptions = {
    fontSize: 14,
    enableBasicAutocompletion: true,
    enableLiveAutocompletion: true,
    enableSnippets: true,
    showLineNumbers: true,
  };

  const [localOutput, setOutput] = useState(output || "");
  const [localLanguage, setLocalLanguage] = useState(language || ""); // Initialize with the provided language prop
  const [localCode, setLocalCode] = useState(code || ""); // Initialize with the provided code prop

  const handleLanguageSelectLocal = (event) => {
    // Update the local state
    const selectedLanguage = event.target.value;
    setLocalLanguage(selectedLanguage);
    
    // Call the parent handleLanguageSelect with the selected language
    if (handleLanguageSelect) {
      handleLanguageSelect(selectedLanguage);
    }
  };

  const handleChangeLocal = (newCode) => {
    // Update the local state
    setLocalCode(newCode);
    
    // Call the parent onChange with the new code
    if (onChange) {
      onChange(newCode);
    }
  };

  const handleRunCode = async () => {
    try {
      console.log({ localLanguage, localCode });
      const response = await axios.post(
        "http://localhost:5001/run",
        { language: localLanguage, code: localCode }

      );

      // if (!response.ok) {
      //   throw new Error("Execution failed");
      // }
      console.log(response.data.output);
      setOutput(response.data.output);
    } catch (error) {
      console.error("Error executing code:", error);
      setOutput("Error executing code");
    }
  };

  return (
    <div className="relative max-w-screen-xl w-1/2 h-1/2 px-4 py-4 sm:px-6 sm:py-8 lg:px-8">
      <div className=" sm:flex sm:items-center sm:justify-between dark:bg-dark">
        <h1 className="text-white font-mono text-center sm:text-left text-xl">
          Code Editor
        </h1>
        <div className="relative">
          <div>
            {/* <select label="Language" className="inline-flex items-center overflow-hidden rounded-md border dark:border-green-600 dark:bg-gray-900 text-white p-1 mr-2">
                
                <option value="JM">C++</option>
                <option value="SRV">Java</option>
                <option value="JH">Python</option>
              </select>*/}
          </div>
        </div>
      </div>

      <div className="h-screen bg-green-600 dark:border-green-600 p-0.5 flex flex-col">
        <div className="flex">
          <div>
          <select
              value={language}
              onChange={handleLanguageSelectLocal}
              label="Language"
              className="sm:text-center inline-flex overflow-hidden font-mono border border-green-600 bg-green-600 px-2 py-1.5 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring"
            >
              {languageList.map((curLanguage, i) => (
                <option value={curLanguage} key={i}>
                  {curLanguage}
                </option>
              ))}
            </select>
          </div>

          <div>
            <select className="sm:text-center inline-flex overflow-hidden font-mono border border-green-600 bg-green-600 px-2 py-1.5 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring">
              <option value="">Theme</option>
              <option value="">Monokai</option>
              <option value="">Solarized</option>
              <option value="">Contrast</option>
            </select>
          </div>
          <div className="relative right-0">
            <button
              variant="contained"
              value="runcode"
              onClick={handleRunCode}
              disabled={runLoading || submitLoading}
              className="sm:text-center item-right inline-flex overflow-hidden font-mono border border-green-600 bg-green-600 px-2 py-1 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring"
            >
              {runLoading === true ? (
                <CircularProgress size={"20px"} style={{ color: "white" }} />
              ) : (
                <span>
                  <PlayCircleOutlineIcon style={{ color: "white" }} />
                  Run
                </span>
              )}
            </button>
          </div>
          <div className="relative float-right">
            <button
              variant="contained"
              value="submit"
              onClick={submit}
              disabled={runLoading || submitLoading}
              className="sm:text-center item-right inline-flex overflow-hidden font-mono border border-green-600 bg-green-600 px-2 py-1.5 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring"
            >
              {submitLoading === true ? (
                <CircularProgress size={"20px"} style={{ color: "white" }} />
              ) : (
                <span>
                  <FontAwesomeIcon
                    icon={faPaperPlane}
                    style={{ marginBottom: "1px" }}
                  />
                  Submit
                </span>
              )}
            </button>
          </div>
        </div>
        <div className="h-3/4 bg-black dark:border-green-600">
          <MonacoEditor
            className="text-green-600"
            height="688"
            language={localLanguage}
            theme="hc-black"
            value={localCode}
            options={editorOptions}
            onChange={handleChangeLocal}
            editorDidMount={editorDidMount}
          />
        </div>
        <div className="flex h-1/4 dark:border-green-600 pt-0.5 ">
          <div className="w-1/2 h-full bg-black text-green-600 dark:border-green-600 resize-none mr-0.5">
            <textarea className="w-full h-full bg-black text-green-600 dark:border-green-600 resize-none mr-0.5"></textarea>
          </div>
          <div className="w-1/2 h-full bg-black text-green-600 dark:border-green-600 resize-none focus:outline-none">
           {localOutput}
          </div>
        </div>
      </div>
    </div>
  );
};

export default CodeEditor;
