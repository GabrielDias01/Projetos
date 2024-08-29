@extends('layouts.app')

@section('content')
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4">
            <div class="card border-light shadow-sm rounded">
                <div class="card-header bg-light text-center border-bottom">
                    <h4 class="mb-0 text-dark">Login</h4>
                </div>
                <div class="card-body">
                    @if ($errors->any())
                        <div class="alert alert-danger mb-4">
                            <ul class="mb-0">
                                @foreach ($errors->all() as $error)
                                    <li>{{ $error }}</li>
                                @endforeach
                            </ul>
                        </div>
                    @endif

                    <form method="POST" action="{{ route('alunos.login') }}">
                        @csrf

                        <!-- Email Input -->
                        <div class="form-group mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" name="email" id="email" class="form-control rounded-pill" placeholder="Digite seu email" required autofocus>
                        </div>

                        <!-- Password Input -->
                        <div class="form-group mb-4">
                            <label for="password" class="form-label">Senha</label>
                            <input type="password" name="password" id="password" class="form-control rounded-pill" placeholder="Digite sua senha" required>
                        </div>

                        <!-- Login Button -->
                        <button type="submit" class="btn btn-primary w-100 rounded-pill">Login</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
