import React, { useState, useEffect } from "react";

export default function FormUser({ setUsers, users, idCounter, setIdCounter, editUser, setEditUser }) {
  const [formData, setFormData] = useState({
    id: idCounter,
    first_name: "",
    last_name: "",
    age: 0,
    gender: "m",
  });

  useEffect(() => {
    if (editUser) {
      setFormData(editUser);
    }
  }, [editUser]);

  function handleSubmit(e) {
    e.preventDefault();

    // Validators
    if (formData.age < 18) {
      alert("Invalid Age!");
      return;
    }

    if (formData.first_name.trim() == "") {
      alert("First Name cannot be empty!");
      return;
    }

    if (formData.last_name.trim() == "") {
      alert("Last Name cannot be empty!");
      return;
    }

    if (formData.gender.trim() == "") {
      alert("Gender cannot be empty!");
      return;
    }

    // State Updates
    if (editUser) {
      setUsers(users.map((user) =>
        user.id == editUser.id ? formData : user
      ));
      setEditUser(null);
    } else {
      setUsers([formData, ...users]);
      setIdCounter(idCounter + 1);
    }

    setFormData({
      id: idCounter + 1,
      first_name: "",
      last_name: "",
      age: 0,
      gender: "m",
    });
  }

//dry

  function handleInputChange(e) {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  }

  return (
    <form onSubmit={handleSubmit} className="border-3 p-5 rounded-lg">
      <div>
        <label htmlFor="first_name">First Name</label>
        <input
          type="text"
          name="first_name"
          value={formData.first_name}
          onChange={handleInputChange}
        />
      </div>

      <div>
        <label htmlFor="last_name">Last Name</label>
        <input
          type="text"
          name="last_name"
          value={formData.last_name}
          onChange={handleInputChange}
        />
      </div>

      <div>
        <label htmlFor="age">Age</label>
        <input
          type="number"
          name="age"
          value={formData.age}
          onChange={handleInputChange}
          min={18}
        />
      </div>

      <div>
        <label htmlFor="gender">Gender</label>
        <select
          name="gender"
          id="gender"
          value={formData.gender}
          onChange={handleInputChange}
        >
          <option value="m">Male</option>
          <option value="f">Female</option>
        </select>
      </div>

      <div>
        <button type="submit">
          {editUser ? "Update" : "Create"}
        </button>
      </div>
    </form>
  );
}
