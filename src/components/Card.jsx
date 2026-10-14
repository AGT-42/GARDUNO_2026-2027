import React from "react";

export default function Card({ user, setEditUser, handleDelete }) {
  return (
    <div className="border-2 p-4 rounded-lg">
      <p>{user?.id || "ID"}</p>
      <p>{user?.first_name || "First Name"}</p>
      <p>{user?.last_name || "Last Name"}</p>
      <p>{user?.age ?? "Age"}</p>
      <p>{user?.gender == "m" ? "Male" : "Female"}</p>

      <div className="flex gap-2 mt-4">
        <button
          className="bg-blue-600 px-4 py-2 rounded text-sm"
          onClick={() => setEditUser(user)}
        >
          Update
        </button>

        <button
          className="bg-red-600 px-4 py-2 rounded text-sm"
          onClick={() => handleDelete(user.id)}
        >
          Delete
        </button>
      </div>
    </div>
  );
}
