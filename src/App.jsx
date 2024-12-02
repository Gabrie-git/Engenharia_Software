import React, { useState } from "react";
import "./App.css";

function App() {
  const [tasks, setTasks] = useState([]);
  const [filter, setFilter] = useState("Todos");
  const [selectedTask, setSelectedTask] = useState(null);

  // Adicionar uma nova tarefa
  const addTask = (task) => {
    setTasks((prevTasks) => [...prevTasks, task]);
  };

  
  return (
    <div className="App">
      <header className="App-header">
        <h1>Gerenciador de Estudos</h1>
      </header>
      <main>
        <div className="content">
          <TaskFilterAndList
            filter={filter}
            setFilter={setFilter}
            tasks={filteredTasks}
            onDeleteTask={deleteTask}
            onSelectTask={setSelectedTask}
          />
          <AddTaskForm onAddTask={addTask} />
        </div>
      </main>
      {selectedTask && (
        <TaskDetailModal
          task={selectedTask}
          onClose={() => setSelectedTask(null)}
          onUpdateTask={updateTask}
        />
      )}
    </div>
  );
}



function AddTaskForm({ onAddTask }) {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [status, setStatus] = useState("Pendente");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!title || !dueDate) {
      alert("Título e Data são obrigatórios.");
      return;
    }
    const newTask = {
      id: Date.now().toString(),
      title,
      description,
      dueDate,
      status,
    };
    onAddTask(newTask);
    setTitle("");
    setDescription("");
    setDueDate("");
    setStatus("Pendente");
  };

  return (
    <form onSubmit={handleSubmit} className="add">
      <h2>Adicionar Nova Tarefa</h2>
      <input
        type="text"
        placeholder="Título"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        required
      />
      <textarea
        placeholder="Descrição"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <input
        type="date"
        value={dueDate}
        onChange={(e) => setDueDate(e.target.value)}
        required
      />
      <select value={status} onChange={(e) => setStatus(e.target.value)}>
        <option value="Pendente">Pendente</option>
        <option value="Em andamento">Em andamento</option>
        <option value="Concluído">Concluído</option>
      </select>
      <button type="submit">Adicionar Tarefa</button>
    </form>
  );
}

function TaskDetailModal({ task, onClose, onUpdateTask }) {
  const [isEditing, setIsEditing] = useState(false);

  if (isEditing) {
    return (
      <EditTaskForm
        task={task}
        onSave={(updatedTask) => {
          onUpdateTask(updatedTask);
          setIsEditing(false);
        }}
        onCancel={() => setIsEditing(false)}
      />
    );
  }

  return (
    <div className="task-detail-modal">
      <div className="task-detail-content">
        <h2>Detalhes da Tarefa</h2>
        <h3>{task.title}</h3>
        <p><strong>Descrição:</strong> {task.description}</p>
        <p><strong>Status:</strong> {task.status}</p>
        <p><strong>Data de Vencimento:</strong> {task.dueDate}</p>
        <button onClick={onClose}>Fechar</button>
        <button className="edit-button" onClick={() => setIsEditing(true)}>
          ✏️ Editar
        </button>
      </div>
    </div>
  );
}



export default App;
