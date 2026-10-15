
import React from "react";
import { Outlet } from "react-router";
import Header from "./components/Header";

export default function App() {
  return (
    <div>
      <Header />

      <main className="min-h-screen w-full bg-linear-to-t from-slate-500 to-taupe-300 text-center pt-10">
        <Outlet />
      </main>
    </div>
  );
}
