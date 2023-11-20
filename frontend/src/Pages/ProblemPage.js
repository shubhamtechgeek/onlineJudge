import React from 'react'
import SpecificProblem from '../Component/Problems/SpecificProblem';
import CodeEditior from '../Component/ExecutionWindow/CodeEditior';
import AlgoJudge from '../Component/AlgoJudge';

const ProblemPage = () => {
  return (
    <div>
      <AlgoJudge/>
      <div className='flex'>
      <SpecificProblem/>
      <CodeEditior/>
      </div>
      
    </div>
  )
}

export default ProblemPage;