import React, { useState } from 'react';
import CodeEditior from './CodeEditior';

const CE = () => {
    const [code, setCode] = useState('// Write your code here');


    const handleCodeChange = (newCode) => {
        setCode(newCode);
      };
    
      return (
        <div>
          <h1 className='text-white font-mono font-xl'>Monaco Editor in React</h1>
          <CodeEditior code={code} onChange={handleCodeChange} />
        </div>
      );
}

export default CE;