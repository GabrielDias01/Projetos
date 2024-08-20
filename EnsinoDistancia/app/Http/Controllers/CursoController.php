<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Curso;

class CursoController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
       $cursos = Curso::all();
       return view('cursos.index', compact('cursos'));
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('cursos.create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $request->validate([
            'nome'=> 'required|string|max:255',
            'descricao'=> 'required',
            'categoria'=> 'required',
            'preco'=> 'required|numeric',
        ]);


        Curso::create($request->all());


        return redirect()->route('cursos.index')->
        with('sucess','Produto Criado com Sucesso');
    }



    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Curso $curso)
    {
        return view('cursos.edit', compact('curso'));
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Curso $curso)
    {
        $request->validate([
            'nome'=> 'required|string|max:255',
            'descricao'=> 'required',
            'categoria'=> 'required',
            'preco'=> 'required|numeric',
        ]);


        Curso::create($request->all());


        return redirect()->route('cursos.index')->
        with('sucess','Produto Criado com Sucesso');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Curso $curso)
    {
        $curso->delete();


        return redirect()->route('cursos.index')->
        with('sucess','Produto Deletado com Sucesso');
    }

    public function show(Curso $produto){
        return view('cursos.show', compact('curso'));
    }
}
