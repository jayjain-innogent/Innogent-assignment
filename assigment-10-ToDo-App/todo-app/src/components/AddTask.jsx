import React, { useState } from "react";

export default function AddTask({ onAdd }) {
    const [task, setTask] = useState("");

    //For handling form submit
    const handleSubmit = (e) => {
        e.preventDefault();
        if (!task.trim()) return; //Check for empty task
        onAdd(task); //Add task using onAdd prop
        setTask(""); //Clear input field
    }

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Enter new task..."
                value={task}
                onChange={(e) => setTask(e.target.value)}
            />

            <button type="submit">Add</button>
        </form>
    );


}