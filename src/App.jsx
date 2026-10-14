import React, { useState } from "react";

import FormUser from "./components/FormUser";
import ContainerUsers from "./components/ContainerUsers";

export default function App() {
  const [users, setUsers] = useState([
    {
      id: 1,
      first_name: "Frieren",
      last_name: "The Slayer",
      age: 1000,
      gender: "f",
    },
    {
      id: 2,
      first_name: "Fern",
      last_name: "The Slayer",
      age: 1000,
      gender: "f",
    },
    {
      id: 3,
      first_name: "Stark",
      last_name: "The Slayer",
      age: 1000,
      gender: "m",
    },
  ]);

  const [idCounter, setIdCounter] = useState(users.length + 1);
  const [editUser, setEditUser] = useState(null);

  return (
    <main className="bg-slate-950 text-white text-6xl min-h-screen flex flex-col items-center gap-8 p-4">
      <h1>Users Gallery</h1>

      {/* Form Section */}
      <FormUser
        setUsers={setUsers}
        users={users}
        idCounter={idCounter}
        setIdCounter={setIdCounter}
        editUser={editUser}
        setEditUser={setEditUser}
      />

      {/* User Gallery Section */}
      <ContainerUsers
        users={users}
        setUsers={setUsers}
        setEditUser={setEditUser}
        editUser={editUser}
      />
    </main>
  );
}
