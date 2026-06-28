import { useEffect, useState } from "react";
import { updateTask } from "../services/taskService";

function EditTaskModal({
    show,
    task,
    onClose,
    onUpdated
}) {

    const [formData, setFormData] = useState({
        title: "",
        description: "",
        status: "TODO",
        priority: "LOW",
        dueDate: "",
        projectId: 0
    });

    useEffect(() => {
        if (task) {
            setFormData({
                title: task.title,
                description: task.description,
                status: task.status,
                priority: task.priority,
                dueDate: task.dueDate,
                projectId: task.projectId
            });
        }
    }, [task]);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await updateTask(task.id, formData);

            alert("Task Updated Successfully!");

            onUpdated();

            onClose();

        } catch (error) {

            console.error(error);

            alert("Failed to update task");

        }

    };

    if (!show) return null;

    return (

        <div className="modal d-block">

            <div className="modal-dialog">

                <div className="modal-content">

                    <div className="modal-header">

                        <h5>Edit Task</h5>

                    </div>

                    <div className="modal-body">

                        <form onSubmit={handleSubmit}>

                            <input
                                className="form-control mb-3"
                                name="title"
                                value={formData.title}
                                onChange={handleChange}
                            />

                            <textarea
                                className="form-control mb-3"
                                name="description"
                                value={formData.description}
                                onChange={handleChange}
                            />

                            <select
                                className="form-select mb-3"
                                name="status"
                                value={formData.status}
                                onChange={handleChange}
                            >
                                <option>TODO</option>
                                <option>DOING</option>
                                <option>DONE</option>
                            </select>

                            <select
                                className="form-select mb-3"
                                name="priority"
                                value={formData.priority}
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
                                value={formData.dueDate}
                                onChange={handleChange}
                            />

                            <button
                                className="btn btn-success me-2"
                            >
                                Update
                            </button>

                            <button
                                type="button"
                                className="btn btn-secondary"
                                onClick={onClose}
                            >
                                Cancel
                            </button>

                        </form>

                    </div>

                </div>

            </div>

        </div>

    );
}

export default EditTaskModal;