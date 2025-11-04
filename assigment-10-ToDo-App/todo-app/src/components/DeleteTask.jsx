import React from "react";

export default function DeleteTask({ task, onDelete }) {
    return (
        <button onClick={() => onDelete(task.id)}>
            Delete
        </button>
    );
}
