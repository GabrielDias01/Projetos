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
    
        // Verifica se o curso já foi adicionado ao carrinho pelo usuário
        $existingCartItem = Carrinho::where('id_user', $userId)
            ->where('id_curso', $cursoId)
            ->first();
    
        if ($existingCartItem) {
            // Redireciona de volta para a página do curso com uma mensagem de erro
            return redirect()->route('cursos.show', $cursoId)
                ->with('error', 'Você já adicionou este curso ao carrinho.');
        }
    
        // Adiciona o curso ao carrinho
        Carrinho::create([
            'id_curso' => $cursoId,
            'id_user' => $userId,
        ]);
    
        // Redireciona de volta para a página do curso com uma mensagem de sucesso
        return redirect()->route('cursos.show', $cursoId)
            ->with('success', 'Curso adicionado ao carrinho com sucesso!');
    }
}
