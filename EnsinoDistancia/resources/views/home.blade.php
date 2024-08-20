@extends('layouts.app')

@section('content')
<div class="container mt-4">
    <h1 class="mb-4">Lista de Cursos</h1>

    <div class="row">
        @foreach ($cursos as $curso)
            <div class="col-md-6 col-lg-4 mb-4">
                <div class="card shadow-sm border-light">
                    <img src="{{ asset('assets/img/img0.png' . $curso->imagem) }}" class="card-img-top" alt="{{ $curso->nome }}">
                    <div class="card-body">
                        <h5 class="card-title">{{ $curso->nome }}</h5>
                        <p class="card-text">{{ $curso->descricao }}</p>
                        <p class="card-text"><strong>Preço:</strong> R$ {{ number_format($curso->preco, 2, ',', '.') }}</p>
                        <a href="{{ route('cursos.show', $curso->id) }}" class="btn btn-primary">Ver Detalhes</a>
                    </div>
                </div>
            </div>
        @endforeach
    </div>
</div>
@endsection
