@extends('layouts.app')

@section('content')
<div class="container">
    <h1>Login</h1>
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
