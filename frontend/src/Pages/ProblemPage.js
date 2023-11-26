import React from 'react'
import SpecificProblem from '../Component/Problems/SpecificProblem';
import AlgoJudge from '../Component/AlgoJudge';
import CodeEditior from '../Component/ExecutionWindow/CodeEditior';

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