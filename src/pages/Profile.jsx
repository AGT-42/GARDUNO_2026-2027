import React from "react";
import { useParams, Navigate } from "react-router";

const users = [
  {
    id: 1,
    fullname: "Ian Sube",
    age: 99,
    description: "An instructor at MFI Polytechnic Institute.",
  },
  {
    id: 2,
    fullname: "Chounce De Guzman",
    age: 10,
    description: "Makulit....",
  },
];

export default function Profile() {
const userParams = useParams();
//convert the ID into a number
const user = users.find((u) => u.id == Number(userParams.id));

  if (!user) {
    return <Navigate to="/not-found" replace />;
  }

  return (
    <div>
      <p>{user.id}</p>
      <p>{user.fullname}</p>
      <p>{user.age}</p>
      <p>{user.description}</p>
    </div>
  );
}