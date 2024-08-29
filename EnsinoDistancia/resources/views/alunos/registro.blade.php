@extends('layouts.app')

@section('content')
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card border-light shadow-sm rounded">
                <div class="card-header bg-light text-center border-bottom">
                    <h4 class="mb-0 text-dark">Registrar-se</h4>
                </div>
                <div class="card-body">
                    <form method="POST" action="{{ route('alunos.registro') }}">
                        @csrf

                        <!-- Nome Input -->
                        <div class="form-group mb-3">
                            <label for="name" class="form-label">Nome</label>
                            <input type="text" name="name" id="name" class="form-control rounded-pill @error('name') is-invalid @enderror" value="{{ old('name') }}" required>
                            @error('name')
                                <div class="invalid-feedback">{{ $message }}</div>
                            @enderror
                        </div>

                        <!-- Email Input -->
                        <div class="form-group mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" name="email" id="email" class="form-control rounded-pill @error('email') is-invalid @enderror" value="{{ old('email') }}" required>
                            @error('email')
                                <div class="invalid-feedback">{{ $message }}</div>
                            @enderror
                        </div>

                        <!-- Senha Input -->
                        <div class="form-group mb-3">
                            <label for="password" class="form-label">Senha</label>
                            <input type="password" name="password" id="password" class="form-control rounded-pill @error('password') is-invalid @enderror" required>
                            @error('password')
                                <div class="invalid-feedback">{{ $message }}</div>
                            @enderror
                        </div>

                        <!-- Confirmar Senha Input -->
                        <div class="form-group mb-4">
                            <label for="password_confirmation" class="form-label">Confirme a Senha</label>
                            <input type="password" name="password_confirmation" id="password_confirmation" class="form-control rounded-pill" required>
                        </div>

                        <!-- Register Button -->
                        <button type="submit" class="btn btn-primary w-100 rounded-pill">Registrar-se</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
