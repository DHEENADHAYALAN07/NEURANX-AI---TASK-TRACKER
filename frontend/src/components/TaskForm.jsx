import { useState } from "react";
import { createTask } from "../services/taskService";

function TaskForm({ onTaskCreated }) {

    const [task, setTask] = useState({
        title: "",
        description: "",
        priority: "LOW",
        dueDate: "",
        projectId: 2
    });

    const handleChange = (e) => {
        setTask({
            ...task,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await createTask(task);

            alert("Task Created Successfully!");

            setTask({
                title: "",
                description: "",
                priority: "LOW",
                dueDate: "",
                projectId: 2
            });

            onTaskCreated();

        } catch (error) {
            console.error(error);
            alert("Failed to create task");
        }
    };

    return (

        <form onSubmit={handleSubmit} className="card p-4 mb-4">

            <h4>Add Task</h4>

            <input
                className="form-control mb-3"
                placeholder="Title"
                name="title"
                value={task.title}
                onChange={handleChange}
            />

            <textarea
                className="form-control mb-3"
                placeholder="Description"
                name="description"
                value={task.description}
                onChange={handleChange}
            />

            <select
                className="form-select mb-3"
                name="priority"
                value={task.priority}
                onChange={handleChange}
            >
                <option>LOW</option>
                <option>MEDIUM</option>
                <option>HIGH</option>
            </select>

            <input
                type="date"
                className="form-control mb-3"
                name="dueDate"
                value={task.dueDate}
                onChange={handleChange}
            />

            <button className="btn btn-primary">
                Save Task
            </button>

        </form>

    );
}

export default TaskForm;