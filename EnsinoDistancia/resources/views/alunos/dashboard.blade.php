@extends('layouts.app')


@section('content')

<div class="container">
    <h1>Dashboard de Cursos</h1>


    <form method="GET" action="{{ route('dashboard') }}">
        <input type="text" name="search" placeholder="Pesquisar Cursos..." value="{{ request('search') }}">
        <button type="submit">Pesquisar</button>
    </form>
    <div class="row">
        @foreach ($cursos as $curso)
            <div class="col-md-4">
                <div class="card">
                    <img src="{{ asset('assets/img/img0.png' . $curso->imagem) }}" class="card-img-top" alt="{{ $curso->nome }}">
                    <div class="card-body">
                        <h5 class="card-title">{{ $curso->nome }}</h5>
                        <p class="card-text">{{ $curso->descricao }}</p>
                        <p class="card-text">Preço: R$ {{ $curso->preco }}</p>
                        <a href="{{ route('cursos.show', $curso->id) }}" class="btn btn-primary">Ver Produto</a>
                    </div>
                </div>
            </div>
        @endforeach
    </div>
</div>
@endsection


