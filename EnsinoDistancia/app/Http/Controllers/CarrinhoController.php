<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Curso;
use App\Models\Carrinho;
use Illuminate\Support\Facades\Auth;

class CarrinhoController extends Controller
{
    public function add(Request $request, Curso $curso)
    {
        $userId = Auth::id();
        $cursoId = $curso->id;
<<<<<<< HEAD
    
=======

>>>>>>> 2d8f044cb6d52423938377f5615c878c75bbe3b5
        // Verifica se o curso já foi adicionado ao carrinho pelo usuário
        $existingCartItem = Carrinho::where('id_user', $userId)
            ->where('id_curso', $cursoId)
            ->first();
<<<<<<< HEAD
    
        if ($existingCartItem) {
            // Redireciona de volta para a página do curso com uma mensagem de erro
            return redirect()->route('cursos.show', $cursoId)
                ->with('error', 'Você já adicionou este curso ao carrinho.');
        }
    
=======

        if ($existingCartItem) {
            return redirect()->route('dashboard')
                ->with('error', 'Você já adicionou este curso ao carrinho.');
        }

>>>>>>> 2d8f044cb6d52423938377f5615c878c75bbe3b5
        // Adiciona o curso ao carrinho
        Carrinho::create([
            'id_curso' => $cursoId,
            'id_user' => $userId,
        ]);
<<<<<<< HEAD
    
        // Redireciona de volta para a página do curso com uma mensagem de sucesso
        return redirect()->route('cursos.show', $cursoId)
=======

        return redirect()->route('dashboard')
>>>>>>> 2d8f044cb6d52423938377f5615c878c75bbe3b5
            ->with('success', 'Curso adicionado ao carrinho com sucesso!');
    }
}
