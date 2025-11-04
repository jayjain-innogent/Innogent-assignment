import React, { useState, useEffect } from "react";
import AddTask from "./components/AddTask.jsx";
import ShowTasks from "./components/ShowTasks.jsx";
import "./App.css";


const KEY = "todos_data_v2";

export default function App() {
  const [todos, setTodos] = useState(
    JSON.parse(localStorage.getItem(KEY)) || []
  );


  // SAVE when todos change
  useEffect(() => {
    localStorage.setItem(KEY, JSON.stringify(todos));
  }, [todos]);

  const handleAdd = (text) => {
    const newTodo = {
      id: Date.now().toString(),
      text: text.trim(),
    };
    setTodos((prev) => [newTodo, ...prev]);
  };

  const handleDelete = (id) => {
    setTodos((prev) => prev.filter((t) => t.id !== id));
  };

  const handleUpdate = (id, newText) => {
    setTodos((prev) =>
      prev.map((t) => (t.id === id ? { ...t, text: newText.trim() } : t))
    );
  };

  return (
    <div className="container">
      <h2>Todo App</h2>

      <AddTask onAdd={handleAdd} />

      <ShowTasks todos={todos} onDelete={handleDelete} onUpdate={handleUpdate} />
    </div>
  );
}

