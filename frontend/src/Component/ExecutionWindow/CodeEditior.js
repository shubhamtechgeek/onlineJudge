import React from 'react';
import MonacoEditor from 'react-monaco-editor'


  const CodeEditor = ({ code, onChange }) => {
    const editorDidMount = (editor, monaco) => {
      console.log('Editor did mount:', editor);
      // You can perform additional setup here
    };

  return (
    <div className="relative max-w-screen-xl w-1/2 px-4 py-4 sm:px-6 sm:py-8 lg:px-8">
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
      <div className='flex'>
      <div>
        <select className='sm:text-center inline-flex overflow-hidden font-mono border border-green-600 bg-green-600 px-2 py-1 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring'>
          <option value="">Language</option>
          <option value="">C++</option>
          <option value="">Java</option>
          <option value="">Python</option>
        </select>
      </div>

      <div>
        <select className='sm:text-center inline-flex overflow-hidden font-mono border border-green-600 bg-green-600 px-2 py-1 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring'>
          <option value="">Theme</option>
          <option value="">Monokai</option>
          <option value="">Solarized</option>
          <option value="">Contrast</option>
        </select>
      </div>
      <div className='relative right-0'>
        <button className='sm:text-center item-right inline-flex overflow-hidden font-mono border border-green-600 bg-green-600 px-2 py-1 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring'>Run</button>
      </div>
      <div className='relative float-right'>
        <button className='sm:text-center item-right inline-flex overflow-hidden font-mono border border-green-600 bg-green-600 px-2 py-1 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring'>Submit</button>
      </div>
      </div>
      <div className="h-3/4 bg-black dark:border-green-600">
      <MonacoEditor 
      className='text-green-600'
            height="688"
            language="java"
            theme="hc-black"
            value={code}
            options={{ fontSize: 14 }}
            onChange={onChange}
            editorDidMount={editorDidMount}
          />
      
          
        </div>
      <div className="flex h-1/4 dark:border-green-600 pt-0.5 ">
        <div className="w-1/2 h-full bg-black text-green-600 dark:border-green-600 resize-none mr-0.5">
        <textarea></textarea>
        </div>
        <div className='w-1/2 h-full bg-black text-green-600 dark:border-green-600 resize-none focus:outline-none'>
        <textarea></textarea>
        </div>
      </div>
    </div>
    </div>

   
  );
};

export default CodeEditor;
