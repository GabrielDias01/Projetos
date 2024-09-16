'use client'; 

import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const router = useRouter();

  const handleLogin = async () => {
    try {
      // Enviar requisição de login
      const response = await fetch('/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      // Verificar se a resposta foi bem-sucedida
      if (response.ok) {
        const { token } = await response.json();
        // Armazenar o token no localStorage
        localStorage.setItem('token', token);
        // Redirecionar para a página de tarefas
        router.push('/tasks');
      } else {
        // Manipular erro retornado pela API
        const errorData = await response.json();
        setError(errorData.message || 'Credenciais inválidas');
      }
    } catch (error) {
      // Manipular erro de rede ou outros erros inesperados
      setError('Erro ao se conectar com o servidor');
    }
  };

  return (
    <div>
      <h1>Login</h1>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>Login</button>
      <p>
        Não tem uma conta? <a href="/register">Registre-se</a>
      </p>
    </div>
  );
}
