import React from 'react'
import {Link} from 'react-router-dom'

const Navbar = () => {


  
  return (
    <div>
        <header>
  <div className="mx-auto max-w-screen-xl px-4 py-8 sm:px-6 sm:py-12 lg:px-8">
    <div className="sm:flex sm:items-center sm:justify-between">
      <div className="text-center sm:text-left">
        <h1 className="text-2xl font-bold text-white sm:text-3xl font-mono">
          AlgoJudge
        </h1>

        <p className="mt-1.5 text-sm font-mono text-green-600">
          This is an online judge which looks good :{")"}
        </p>
      </div>

      <div className="mt-4 flex flex-col gap-4 sm:mt-0 sm:flex-row sm:items-center">
        <button
          className="inline-flex items-center justify-center gap-1.5 rounded-lg border border-green-600 bg-green-600 px-5 py-3 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring"
          type="button"
        >
          <span className="text-sm font-medium font-mono text-white"> Github </span>

          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="h-4 w-4"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            strokeWidth="2"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14"
            />
          </svg>
        </button>
        <span></span>
        <button
          className="font-mono block rounded-lg  border border-green-600 bg-green-600 px-5 py-3 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring"
          type="button"
        >
          <Link to="/login">Login / Signup</Link>
        </button>
      </div>
    </div>
  </div>
</header>
    </div>
  )
}

export default Navbar;