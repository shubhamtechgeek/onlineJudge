import React from 'react'
import { Link } from 'react-router-dom'

const Profile = () => {
  return (
    <div className='dark:bg-black' >
        <h1 className='text-white font-mono' >Profile</h1>
        <div>
        <h1 className='text-white font-mono' ><Link to="/logout">Logout</Link> </h1>
        </div>
    </div>
  )
}

export default Profile