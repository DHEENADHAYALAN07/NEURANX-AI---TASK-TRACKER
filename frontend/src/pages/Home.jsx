import { useEffect, useState } from "react";
import { getTasks, deleteTask } from "../services/taskService";
import TaskForm from "../components/TaskForm";
import EditTaskModal from "../components/EditTaskModal";    



function Home() {

    const [tasks, setTasks] = useState([]);
    const [status, setStatus] = useState("");
    const [priority, setPriority] = useState("");
    const [direction, setDirection] = useState("asc");
    const [page, setPage] = useState(0);
    const [size] = useState(5);
    const [totalPages, setTotalPages] = useState(0);
    const [editingTask, setEditingTask] = useState(null);
    const [showEditModal, setShowEditModal] = useState(false);

    useEffect(() => {
        loadTasks();
    }, [status, priority, direction, page]);

    const loadTasks = async () => {
        try {
            const response = await getTasks(status, priority,page,size, "dueDate", direction);
            setTasks(response.data.content);
            setTotalPages(response.data.totalPages);
        } catch (error) {
            console.error(error);
        }
    };


    const handleDelete = async (id) => {
        const confirmDelete = window.confirm("Are you sure you want to delete this task?");

        if (!confirmDelete) {
            return;
        }

        try {
            await deleteTask(id);
            loadTasks(); // Refresh the table
        } catch (error) {
            console.error(error);
            alert("Failed to delete task");
        }
    };

    const handleEdit = (task) => {
        setEditingTask(task);
        setShowEditModal(true);
    }

    return (
        <div className="container mt-5">

            <h1 className="mb-4 text-center">
                Task Tracker
            </h1>

            <TaskForm onTaskCreated={loadTasks} />



            <div className="row mb-3">

                {/* Status Filter */}

                <div className="col-md-4">

                    <select
                        className="form-select"
                        value={status}
                        onChange={(e) => setStatus(e.target.value)}
                    >
                        <option value="">All Status</option>
                        <option value="TODO">TODO</option>
                        <option value="DOING">DOING</option>
                        <option value="DONE">DONE</option>
                    </select>

                </div>

                {/* Priority Filter */}

                <div className="col-md-4">

                    <select
                        className="form-select"
                        value={priority}
                        onChange={(e) => setPriority(e.target.value)}
                    >
                        <option value="">All Priority</option>
                        <option value="LOW">LOW</option>
                        <option value="MEDIUM">MEDIUM</option>
                        <option value="HIGH">HIGH</option>
                    </select>

                </div>

                {/* Sort Direction Filter */}

                <div className="col-md-4">

                    <select
                        className="form-select"
                        value={direction}
                        onChange={(e) => setDirection(e.target.value)}
                    >
                        <option value="asc">Due Date ↑</option>
                        <option value="desc">Due Date ↓</option>
                    </select>

                </div>

            </div>

            <table className="table table-bordered table-hover">

                <thead className="table-dark">

                    <tr>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Priority</th>
                        <th>Due Date</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>

                </thead>

                <tbody>

                    {tasks.map(task => (

                        <tr key={task.id}>
                            <td>{task.title}</td>
                            <td>{task.status}</td>
                            <td>{task.priority}</td>
                            <td>{task.dueDate}</td>

                            <td>
                                <button className="btn btn-warning btn-sm" onClick={() => { handleEdit(task);}}>
                                    Edit
                                </button>
                            </td>

                            <td>
                                <button className="btn btn-danger btn-sm" onClick={() => handleDelete(task.id)}>
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}

                </tbody>

            </table>

            <div className="d-flex justify-content-between align-items-center mt-3">

                <button
                    className="btn btn-secondary"
                    disabled={page === 0}
                    onClick={() => setPage(page - 1)}
                >
                    Previous
                </button>

                <span>
                    Page {page + 1} of {totalPages}
                </span>

                <button
                    className="btn btn-secondary"
                    disabled={page + 1 >= totalPages}
                    onClick={() => setPage(page + 1)}
                >
                    Next
                </button>

            </div>

            <EditTaskModal
                show={showEditModal}
                task={editingTask}
                onClose={() => setShowEditModal(false)}
                onUpdated={() => {
                    loadTasks();
                    setShowEditModal(false);
                }}
            />

        </div>
    );


}

export default Home;