import React from 'react'
import {Link} from 'react-router-dom'

const Navbar = () => {

  const token = localStorage.getItem('token');
  
  return (
    <div>
        <header>
  <div className="mx-auto max-w-screen-xl px-4 py-4 sm:px-6 sm:py-8 lg:px-8">
    <div className="sm:flex sm:items-center sm:justify-between">
      <div className="text-center sm:text-left">
        <h1 className="text-2xl font-bold text-white sm:text-3xl font-mono">
          <Link to="/">AlgoJudge</Link>
        </h1>

        <p className="mt-1.5 text-sm font-mono text-green-600">
          This is an online judge which looks good :{")"}
        </p>
      </div>

      <div className="mt-4 flex flex-col gap-4 sm:mt-0 sm:flex-row sm:items-center">
      {token ? (
            <button
            className="font-mono block rounded-lg border border-green-600 bg-green-600 px-5 py-3 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring"
            type="button"
          >
            <Link to="/profile">Profile</Link>
          </button>
          ) : (
            <button
              className="font-mono block rounded-lg border border-green-600 bg-green-600 px-5 py-3 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring"
              type="button"
            >
              <Link to="/login">Login / Signup</Link>
            </button>
          )}
        
        <button
          className="inline-flex items-center justify-center gap-1.5 rounded-lg border border-green-600 bg-green-600 px-5 py-3 text-sm font-medium text-white transition hover:bg-black focus:outline-none focus:ring"
          type="button"
          
        ><Link target='_blank' rel="noopener noreferrer" to="https://github.com/shubhamtechgeek/onlineJudge/">
          <span className="text-sm font-medium font-mono text-white"> Github </span>
        </Link>
        </button>
      </div>
    </div>
  </div>
</header>
    </div>
  )
}

export default Navbar;