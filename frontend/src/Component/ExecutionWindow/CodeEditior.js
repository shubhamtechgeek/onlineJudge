import React, { useState } from 'react';
import { Controlled as CodeMirror } from 'react-codemirror2';
import 'D:/onlineJudge/frontend/src/codemirror/lib/codemirror.css'


const CodeEditior = () => {

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
              </select>
              <select label="Theme" className="inline-flex items-center overflow-hidden rounded-md border dark:border-green-600 dark:bg-gray-900 text-white p-1">
                
                <option value="JM">Monokai</option>
                <option value="SRV">Solarized</option>
                <option value="JH">lq-dark</option>
              </select> */}
          </div>
        </div>
      </div>
      
      <div className="h-screen bg-green-600 dark:border-green-600 p-0.5 flex flex-col">
      <div className="h-3/4 bg-green-600 dark:border-green-600">
            
        </div>
      <div className="flex h-1/4 dark:border-green-600 pt-0.5 ">
        <div className="w-1/2 h-full bg-black text-green-600 dark:border-green-600 resize-none mr-0.5">
        {/* xxxx */}
        </div>
        <div className='w-1/2 h-full bg-black text-green-600 dark:border-green-600 resize-none focus:outline-none'>
        {/* xxxx */}
        </div>
      </div>
    </div>
    </div>
  );
};

export default CodeEditior;
