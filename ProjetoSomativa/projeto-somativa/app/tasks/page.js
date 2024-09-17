'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import 'bootstrap/dist/css/bootstrap.min.css'; // Importa o Bootstrap globalmente
import styles from '../page.module.css'; // Ajuste o caminho conforme necessário

export default function TasksPage() {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState('');
  const [editTaskId, setEditTaskId] = useState(null);
  const [editTitle, setEditTitle] = useState('');
  const [editStatus, setEditStatus] = useState('');
  const [isAdmin, setIsAdmin] = useState(false);
  const [user, setUser] = useState(null);
  const router = useRouter();

  useEffect(() => {
    const fetchTasks = async () => {
      const token = localStorage.getItem('token');
      if (!token) {
        router.push('/login');
        return;
      }

      try {
        const userResponse = await fetch('/api/user', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (!userResponse.ok) {
          throw new Error('Não foi possível obter informações do usuário');
        }

        const userData = await userResponse.json();
        setIsAdmin(userData.isAdmin);
        setUser(userData);

        const response = await fetch('/api/tasks', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (response.ok) {
          const data = await response.json();
          setTasks(Array.isArray(data) ? data : []);
        } else {
          router.push('/login');
        }
      } catch (error) {
        console.error('Erro ao buscar tarefas ou usuário', error);
        router.push('/login');
      }
    };

    fetchTasks();
  }, [router]);

  const addTask = async () => {
    if (!isAdmin) {
      console.error('Somente administradores podem criar tarefas');
      return;
    }

    const token = localStorage.getItem('token');
    const response = await fetch('/api/tasks', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ title: newTask }),
    });

    if (response.ok) {
      const data = await response.json();
      setTasks(prevTasks => [...prevTasks, data]);
      setNewTask('');
    } else {
      console.error('Erro ao criar tarefa');
    }
  };

  const deleteTask = async (id) => {
    const token = localStorage.getItem('token');
    await fetch(`/api/tasks`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ id }),
    });
    setTasks(tasks.filter((task) => task._id !== id));
  };

  const startEditTask = (task) => {
    setEditTaskId(task._id);
    setEditTitle(task.title);
    setEditStatus(task.status);
  };

  const updateTask = async () => {
    const token = localStorage.getItem('token');
    const response = await fetch(`/api/tasks`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ id: editTaskId, title: editTitle, status: editStatus }),
    });

    if (response.ok) {
      const data = await response.json();
      setTasks(
        tasks.map((task) => (task._id === data._id ? data : task))
      );
      setEditTaskId(null);
      setEditTitle('');
      setEditStatus('');
    }
  };

  const handleStatusChange = (event) => {
    setEditStatus(event.target.value);
  };

  return (
    <div>
      {/* Navbar fora do container, ocupando toda a largura */}
      <nav className={`${styles.navbar} navbar navbar-expand-lg `}>
        <div className="container-fluid">
          <div className={styles.logo}></div> {/* Logo está configurada no CSS */}
          {user ? (
            <div className={styles.userProfile}>
              <div className={styles.userName}>{user.username || 'Usuário'}</div>
            </div>
          ) : (
            <div className={styles.userProfile}>Carregando...</div>
          )}
        </div>
      </nav>

      {/* O container principal tem um max-width para manter as proporções */}
      <div className="container mt-4" style={{ maxWidth: '960px' }}>
        <h1 className="mb-4">Folha de Montagem</h1>
        
        {isAdmin && (
          <div className="mb-3">
            <input
              type="text"
              placeholder="Nova tarefa"
              value={newTask}
              onChange={(e) => setNewTask(e.target.value)}
              className="form-control"
            />
            <button onClick={addTask} className="btn btn-primary mt-3">Adicionar Tarefa</button>
          </div>
        )}

        <ul className="list-group">
          {Array.isArray(tasks) && tasks.map((task) => (
            <li key={task._id} className="list-group-item d-flex justify-content-between align-items-center">
              {editTaskId === task._id ? (
                <div className="w-100">
                  <input
                    type="text"
                    value={editTitle}
                    onChange={(e) => setEditTitle(e.target.value)}
                    className="form-control mb-2"
                  />
                  <select value={editStatus} onChange={handleStatusChange} className="form-select">
                    <option value="Pendente">Pendente</option>
                    <option value="Concluída">Concluída</option>
                    <option value="Parado">Parado</option>
                  </select>
                  <button onClick={updateTask} className="btn btn-success mt-2">Salvar</button>
                </div>
              ) : (
                <>
                  <span>
                    Folha de Montagem: {task.title} | Status: {task.status}
                  </span>
                  <div>
                    {isAdmin && (
                      <button onClick={() => deleteTask(task._id)} className="btn btn-danger ms-2">Excluir</button>
                    )}
                    <button onClick={() => startEditTask(task)} className="btn btn-secondary ms-2">Editar</button>
                  </div>
                </>
              )}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
