@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row">
        @foreach ($cursos as $curso)
            <div class="col-md-4 col-lg-2">
                <div class="card mb-4">
                    <img src="/assets/img/img0.png" class="card-img-top" alt="{{ $curso->nome }}">
                    <div class="card-body">
                        <h5 class="card-title">{{ $curso->nome }}</h5>
                        <p class="card-text">{{ $curso->descricao }}</p>
                        <p class="card-text">Preço: R$ {{ $curso->preco }}</p>
                    </div>
                </div>
            </div>
        @endforeach
    </div>
</div>
@endsection
