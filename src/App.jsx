import React, { useState } from "react";
import "./App.css";

function App() {
  const [tasks, setTasks] = useState([]);
  const [filter, setFilter] = useState("Todos");
  const [selectedTask, setSelectedTask] = useState(null); // Estado para armazenar a tarefa selecionada

  const addTask = (task) => {
    setTasks([...tasks, task]);
  };


  const updateTask = (updatedTask) => {
    setTasks(tasks.map(task => task.title === updatedTask.title ? updatedTask : task));
    setSelectedTask(updatedTask); // Atualiza a tarefa selecionada
  };

  const filteredTasks = tasks.filter((task) =>
    filter === "Todos" ? true : task.status === filter
  );

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
            onSelectTask={setSelectedTask} // Passar a função para selecionar tarefa
          />
          <AddTaskForm onAddTask={addTask} />
        </div>
      </main>
      {/* Modal ou Caixa de Visualização de Tarefa */}
      {selectedTask && (
        <div className="task-detail-modal">
          <div className="task-detail-content">
            <h2>Detalhes da Tarefa</h2>
            <h3>{selectedTask.title}</h3>
            <p><strong>Descrição:</strong> {selectedTask.description}</p>
            <p><strong>Status:</strong> {selectedTask.status}</p>
            <p><strong>Data de Vencimento:</strong> {selectedTask.dueDate}</p>
            <button onClick={() => setSelectedTask(null)}>Fechar</button>
            
            {/* Ícone de lápis para editar */}
            <button className="edit-button" onClick={() => setSelectedTask({...selectedTask, isEditing: true})}>
              ✏ Editar
            </button>
          </div>
          
          {/* Caixa de edição de tarefa */}
          {selectedTask.isEditing && (
            <EditTaskForm
              task={selectedTask}
              onSave={updateTask}
              onCancel={() => setSelectedTask({ ...selectedTask, isEditing: false })}
            />
          )}
        </div>
      )}
    </div>
  );
}

function TaskFilterAndList({ filter, setFilter, tasks, onDeleteTask, onSelectTask }) {
  return (
    <div className="task-filter-list">
      {/* Seção de filtro */}
      <div className="task-filter">
        <h2>Filtrar Tarefas</h2>
        <select value={filter} onChange={(e) => setFilter(e.target.value)}>
          <option value="Todos">Todos</option>
          <option value="Pendente">Pendente</option>
          <option value="Em andamento">Em andamento</option>
          <option value="Concluído">Concluído</option>
        </select>
      </div>

      {/* Seção de tarefas */}
      <div className="task-list">
        <h2>Suas Tarefas</h2>
        {tasks.length === 0 ? (
          <p>Não há tarefas a exibir.</p>
        ):(
          <ul>
            {tasks.map((task, index) => (
              <li
                key={index}
                className={'task-${task.status.toLowerCase().replace(" ", "-")}'}
                onClick={() => onSelectTask(task)} // Seleciona a tarefa ao clicar
              >
                <h3>{task.title}</h3>
                <p>{task.description}</p>
                <p>
                  <strong>Status:</strong> {task.status}
                </p>
                <p>
                  <strong>Data:</strong> {task.dueDate}
                </p>
                <button onClick={() => onDeleteTask(index)}>Excluir</button>
              </li>
            ))}
          </ul>
        )}
      </div>
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
    onAddTask({ title, description, dueDate, status });
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

function EditTaskForm({ task, onSave, onCancel }) {
  const [title, setTitle] = useState(task.title);
  const [description, setDescription] = useState(task.description);
  const [dueDate, setDueDate] = useState(task.dueDate);
  const [status, setStatus] = useState(task.status);

  const handleSave = (e) => {
    e.preventDefault();
    const updatedTask = { ...task, title, description, dueDate, status, isEditing: false };
    onSave(updatedTask);
  };

  return (
    <form onSubmit={handleSave} className="edit">
      <h2>Editar Tarefa</h2>
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
      <button type="submit">Salvar Alterações</button>
      <button type="button" onClick={onCancel}>Cancelar</button>
    </form>
  );
}

export default App;