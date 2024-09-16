'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import styles from '../page.module.css';
import '../globals.css';

export default function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const router = useRouter();

    const handleLogin = async () => {
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password }),
        });

        if (response.ok) {
            router.push('/dashboard');
        } else {
            setError('Erro ao fazer login');
        }
    };

    return (
        <div className={styles.container}>
            <div className={styles.authContainer}>
                <h1>Login</h1>
                {error && <p className={styles.errorMessage}>{error}</p>}
                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    className={styles.input}
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className={styles.input}
                />
                <button onClick={handleLogin} className={styles.button}>
                    Login
                </button>
                <p className={styles.text}>
                    Não tem uma conta? <a href="/register" className={styles.link}>Registre-se</a>
                </p>
            </div>
        </div>
    );
}
