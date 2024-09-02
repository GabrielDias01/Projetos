import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

function EditarLivro() {
    const { id } = useParams();
    const [titulo, setTitulo] = useState('');
    const [autor, setAutor] = useState('');
    const [ano, setAno] = useState('');
    const [genero, setGenero] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`http://localhost:5000/livros/${id}`)
            .then(response => {
                setTitulo(response.data.titulo);
                setAutor(response.data.autor);
                setAno(response.data.ano);
                setGenero(response.data.genero);
            })
            .catch(error => console.error('Erro ao buscar o livro:', error));
    }, [id]);

    const atualizarLivro = (e) => {
        e.preventDefault();
        axios.put(`http://localhost:5000/livros/${id}`, { titulo, autor, ano, genero })
            .then(() => navigate('/'))
            .catch(error => console.error('Erro ao atualizar livro:', error));
    };

    const formStyle = {
        maxWidth: '600px',
        margin: '0 auto',
        padding: '20px',
        borderRadius: '8px',
        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
        backgroundColor: '#f9f9f9',
    };

    const inputStyle = {
        width: '100%',
        padding: '10px',
        margin: '10px 0',
        border: '1px solid #ccc',
        borderRadius: '4px',
        boxSizing: 'border-box',
    };

    const buttonStyle = {
        width: '100%',
        padding: '10px',
        backgroundColor: '#007bff',
        color: '#fff',
        border: 'none',
        borderRadius: '4px',
        cursor: 'pointer',
        fontSize: '16px',
        fontWeight: 'bold',
        transition: 'background-color 0.3s ease',
    };

    const buttonHoverStyle = {
        backgroundColor: '#0056b3',
    };

    return (
        <form onSubmit={atualizarLivro} style={formStyle}>
            <h1 style={{ textAlign: 'center', marginBottom: '20px' }}>Editar Livro</h1>
            <input 
                type="text" 
                value={titulo} 
                onChange={e => setTitulo(e.target.value)} 
                placeholder="Título" 
                required 
                style={inputStyle}
            />
            <input 
                type="text" 
                value={autor} 
                onChange={e => setAutor(e.target.value)} 
                placeholder="Autor" 
                required 
                style={inputStyle}
            />
            <input 
                type="number" 
                value={ano} 
                onChange={e => setAno(e.target.value)} 
                placeholder="Ano de Publicação" 
                required 
                style={inputStyle}
            />
            <input 
                type="text" 
                value={genero} 
                onChange={e => setGenero(e.target.value)} 
                placeholder="Gênero" 
                required 
                style={inputStyle}
            />
            <button 
                type="submit" 
                style={buttonStyle}
                onMouseOver={e => e.currentTarget.style.backgroundColor = buttonHoverStyle.backgroundColor}
                onMouseOut={e => e.currentTarget.style.backgroundColor = buttonStyle.backgroundColor}
            >
                Atualizar
            </button>
        </form>
    );
}

export default EditarLivro;
