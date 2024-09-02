import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function ListaLivros() {
    const [livros, setLivros] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:5000/livros')
            .then(response => setLivros(response.data))
            .catch(error => console.error('Erro ao buscar os livros:', error));
    }, []);

    const deletarLivro = (id) => {
        axios.delete(`http://localhost:5000/livros/${id}`)
            .then(() => setLivros(livros.filter(livro => livro._id !== id)))
            .catch(error => console.error('Erro ao deletar o livro:', error));
    };

    const containerStyle = {
        padding: '20px',
        maxWidth: '800px',
        margin: '0 auto',
    };

    const titleStyle = {
        textAlign: 'center',
        marginBottom: '20px',
    };

    const linkStyle = {
        display: 'block',
        margin: '10px 0',
        padding: '10px',
        color: '#007bff',
        textDecoration: 'none',
        fontSize: '18px',
        fontWeight: 'bold',
        textAlign: 'center',
    };

    const listStyle = {
        listStyleType: 'none',
        padding: '0',
    };

    const listItemStyle = {
        padding: '10px',
        marginBottom: '10px',
        border: '1px solid #ddd',
        borderRadius: '4px',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
    };

    const buttonStyle = {
        backgroundColor: '#dc3545',
        color: '#fff',
        border: 'none',
        borderRadius: '4px',
        padding: '5px 10px',
        cursor: 'pointer',
        fontSize: '14px',
        fontWeight: 'bold',
        transition: 'background-color 0.3s ease',
    };

    const buttonHoverStyle = {
        backgroundColor: '#c82333',
    };

    const editLinkStyle = {
        color: '#007bff',
        textDecoration: 'none',
        fontSize: '14px',
        marginRight: '10px',
    };

    return (
        <div style={containerStyle}>
            <h1 style={titleStyle}>Lista de Livros</h1>
            <Link to="/novo" style={linkStyle}>Adicionar Novo Livro</Link>
            <ul style={listStyle}>
                {livros.map(livro => (
                    <li key={livro._id} style={listItemStyle}>
                        <span>{livro.titulo} - {livro.autor}</span>
                        <div>
                            <Link to={`/editar/${livro._id}`} style={editLinkStyle}>Editar</Link>
                            <button 
                                onClick={() => deletarLivro(livro._id)} 
                                style={buttonStyle}
                                onMouseOver={e => e.currentTarget.style.backgroundColor = buttonHoverStyle.backgroundColor}
                                onMouseOut={e => e.currentTarget.style.backgroundColor = buttonStyle.backgroundColor}
                            >
                                Deletar
                            </button>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default ListaLivros;
