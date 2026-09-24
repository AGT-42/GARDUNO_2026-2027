import React, { useState } from "react";


    const colors = ['black', 'darkgrey', 'mediumdarkgrey', 'mediumgrey', 'mediumlightgrey', 'lightgrey', 'white' ]

    const colorClasses = {
        black: "bg-slate-950",
        darkgrey: "bg-slate-800",
        mediumdarkgrey:"bg-slate-600",
        mediumgrey:"bg-slate-500",
        mediumlightgrey:"bg-slate-400",
        lightgrey:"bg-slate-200",
        white:"bg-slate-100"
    };

    export default function Box({num}){
    const[currentColor, setCurrentColor] = useState(0);

    function handleChangeColor(){
        console.log(currentColor)
        setCurrentColor((currentColor + 1) % colors.length);
    }
    return (
    <button
    onClick={handleChangeColor}
    className= {`w-24 h-24 ${colorClasses[colors[currentColor]]} rounded-lg flex items-center justify-center text-white text-3x1 font-bold border`}
    >{num}
    </button>
    );
    }

