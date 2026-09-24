import React from "react";
import Box from "./components/Box";

const boxes = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

export default function App(){
return (
    <main className="min-h-screen bg-zinc-400  p-4 text-black text-5xl flex justify-center items-center flex-col gap-8">
    <h1>Click the Boxes</h1>

    <div className="flex gap-4">
        {boxes.map((item) => (
        <Box key={item} num={item} />
        ))}
    </div>
    </main>
);
}