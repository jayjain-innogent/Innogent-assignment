import React, { useState } from "react";
import DeleteTask from "./DeleteTask";
import EditTask from "./EditTask";

export default function ShowTasks({ todos, onDelete, onUpdate }) {
    const [editingId, setEditingId] = useState(null);

    return (
        <div>
            <h3>All Tasks</h3>

            <ul>
                {todos.map((t) => (
                    <li className="task-row" key={t.id}>
                        {editingId === t.id ? (
                            <EditTask
                                task={t}
                                onSave={(id, text) => {
                                    onUpdate(id, text);
                                    setEditingId(null);
                                }}
                            />
                        ) : (
                            <>
                                <span className="task-text">{t.text}</span>

                                <div className="task-actions">
                                    <button
                                        className="edit-btn"
                                        onClick={() => setEditingId(t.id)}
                                    >
                                        Edit
                                    </button>

                                    <DeleteTask task={t} onDelete={onDelete} />
                                </div>
                            </>
                        )}
                    </li>
                ))}
            </ul>
        </div>
    );
}
