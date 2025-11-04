import React, { useState } from "react";

export default function EditTask({ task, onSave }) {
    const [text, setText] = useState(task.text);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!text.trim()) return;
        onSave(task.id, text);
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={text}
                onChange={(e) => setText(e.target.value)}
            />

            <button type="submit">Save</button>
        </form>
    );
}
