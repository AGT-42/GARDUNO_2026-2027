import React from 'react';
import drawingImage from '../assets/Drawing.jpeg';
import { Outlet, useNavigate } from "react-router";

export default function NotFound() {
    const nav = useNavigate()
    
    return (
        <button onClick={() => navigation(-1)}>Brah bumalik ka</button>
    );
}