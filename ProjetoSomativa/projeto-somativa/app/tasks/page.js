'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
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
        console.log('Dados do usuário:', userData); // Verifique os dados recebidos

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
      <nav className={styles.navbar}>
        <div className={styles.logo}></div> {/* Logo está configurada no CSS */}
        {user ? (
          <div className={styles.userProfile}>
            <div className={styles.userName}>{user.username || 'Usuário'}</div>
          </div>
        ) : (
          <div className={styles.userProfile}>Carregando...</div>
        )}
      </nav>
      <h1>Folha de Montagem</h1>
      {isAdmin && (
        <>
          <input
            type="text"
            placeholder="Nova tarefa"
            value={newTask}
            onChange={(e) => setNewTask(e.target.value)}
          />
          <button onClick={addTask}>Adicionar Tarefa</button>
        </>
      )}
      <ul>
        {Array.isArray(tasks) && tasks.map((task) => (
          <li key={task._id}>
            {editTaskId === task._id ? (
              <>
                <input
                  type="text"
                  value={editTitle}
                  onChange={(e) => setEditTitle(e.target.value)}
                />
                <select value={editStatus} onChange={handleStatusChange}>
                  <option value="Pendente">Pendente</option>
                  <option value="Concluída">Concluída</option>
                  <option value="Parado">Parado</option>
                </select>
                <button onClick={updateTask}>Salvar</button>
              </>
            ) : (
              <>
                Folha de Montagem:  {task.title} Status: {task.status}
                {isAdmin && (
                  <>
                    <button onClick={() => deleteTask(task._id)}>Excluir</button>
                  </>
                )}
                <button onClick={() => startEditTask(task)}>Editar</button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
