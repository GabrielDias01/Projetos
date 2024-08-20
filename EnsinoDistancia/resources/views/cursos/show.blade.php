@extends('layouts.app')


@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <img src="/assets/img/img0.png" class="img-fluid" alt="{{ $curso->nome }}">
            </div>
            <div class="col-md-6">
                <h2>{{ $curso->nome }}</h2>
                <p>{{ $curso->categoria }}</p>
                <p>{{ $curso->descricao }}</p>
                <p>Preço: R$ {{ $curso->preco }}</p>


                <form method="POST" action="{{ route('carrinho.add', $curso->id) }}">
                    @csrf
                    <button type="submit" class="btn btn-primary">Adicionar ao Carrinho</button>
                </form>
                
                
                
            </div>
        </div>
    </div>
@endsection
