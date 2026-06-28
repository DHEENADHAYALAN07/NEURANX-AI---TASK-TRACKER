import api from "../api/axios";

export const getTasks = (
    status = "",
    priority = "",
    page = 0,
    size = 10,
    sortBy = "dueDate",
    direction = "asc"
) => {

    return api.get("/tasks", {
        params: {
            status,
            priority,
            page,
            size,
            sortBy,
            direction
        }
    });
};

export const getTask = (id) => api.get(`/tasks/${id}`);

export const createTask = (task) => api.post("/tasks", task);

export const updateTask = (id, task) =>
    api.put(`/tasks/${id}`, task);


export const deleteTask = (id) => api.delete(`/tasks/${id}`);