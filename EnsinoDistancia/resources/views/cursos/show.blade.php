@extends('layouts.app')

@section('content')


<div class="container">
    <h1 class="my-4">cursos</h1>


    @if ($message = Session::get('success'))
        <div class="alert alert-success">
            <p>{{ $message }}</p>
        </div>
    @endif


    <a class="btn btn-success mb-2" href="{{ route('cursos.create') }}"> Criar Novo Curso</a>


    <table class="table table-bordered">
        <tr>
            <th>No</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Categoria</th>
            <th>Quantidade</th>
            <th>Preço</th>
            <th width="280px">Ação</th>
        </tr>
        @foreach ($cursos as $curso)
        <tr>
            <td>{{ $loop->iteration }}</td>
            <td>{{ $curso->nome }}</td>
            <td>{{ $curso->descricao }}</td>
            <td>{{ $curso->categoria}}</td>
            <td>{{ $curso->quantidade}}</td>
            <td>{{ $curso->preco }}</td>
            <td>
                <form action="{{ route('cursos.destroy', $curso->id) }}" method="POST">
                    <a class="btn btn-info" href="{{ route('cursos.show', $curso->id) }}">Mostrar</a>
                    <a class="btn btn-primary" href="{{ route('cursos.edit', $curso->id) }}">Editar</a>


                    @csrf
                    <button type="submit" class="btn btn-primary">Adicionar ao Carrinho</button>
                </form>
            </td>
        </tr>
        @endforeach
    </table>
</div>
@endsection
