import React from 'react'
import { Outlet, useNavigate } from "react-router";

export default function Header() {
    const nav = useNavigate()
return (
    
    <header className="flex justify-between bg-slate-800 text-white">
    <h1>WElcoime</h1>

    <nav>
        <button onClick={() => nav('/')} className='px-5'>Dashboard</button>
        <button onClick={() => nav('/login')} className='px-10'>Login</button>
        <button onClick={() => nav('/profile/1')} className='px-10'>Profile</button>
    </nav>
    </header>
  )
}
