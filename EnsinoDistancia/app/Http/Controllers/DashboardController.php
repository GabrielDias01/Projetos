<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Curso;

class DashboardController extends Controller
{
    public function index(Request $request)
    {
        $search = $request->input('search');
        $cursos = Curso::when($search, function ($query, $search) {
            return $query->where('nome', 'like', "%{$search}%")
                         ->orWhere('descricao', 'like', "%{$search}%")
                         ->orWhere('categoria', 'like', "%{$search}%");
        })->get();


        return view('alunos.dashboard', compact('cursos'));
    }
}
