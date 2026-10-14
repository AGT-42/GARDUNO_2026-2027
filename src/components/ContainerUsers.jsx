import React from "react";
import Card from "./Card";

export default function ContainerUsers({ users = [], setUsers, setEditUser }) {

  function handleDelete(id) {
    if (window.confirm("Are you sure you want to delete this user?")) {
      setUsers(users.filter((user) => user.id != id));
    }
  }

  return (
    <div className="flex-1 w-full flex border rounded-lg p-4 gap-4 flex-wrap">
      {users.map((user) => (
        <Card
          key={user.id}
          user={user}
          setEditUser={setEditUser}
          handleDelete={handleDelete}
        />
      ))}
    </div>
  );
}
