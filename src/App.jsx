// App.js
import React, { useState } from "react";
import "./App.css";

function App() {
  const [tasks, setTasks] = useState([]);

  const addTask = (task) => {
    setTasks([...tasks, task]);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Gerenciador de Estudos</h1>
      </header>
      <main>
        <TaskList tasks={tasks} />
        <AddTaskForm onAddTask={addTask} />
      </main>
    </div>
  );
}

function TaskList({ tasks }) {
  return (
    <div>
      <h2>Suas Tarefas</h2>
      <ul>
        {tasks.map((task, index) => (
          <li key={index}>
            <h3>{task.title}</h3>
            <p>{task.description}</p>
            <p>Status: {task.status}</p>
            <p>Data: {task.dueDate}</p>
          </li>
        ))}
      </ul>
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
    onAddTask({ title, description, dueDate, status });
    setTitle("");
    setDescription("");
    setDueDate("");
    setStatus("Pendente");
  };

  return (
    <form onSubmit={handleSubmit}>
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

export default App;
