<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UserController;
use App\http\Controllers\ProdutoController;

Route::get('/', function () {
    return view('home');
});

Route::get('/registro', [UserController::class, 'showRegistroForm'])->name('usuarios.registro');
Route::post('/registro', [UserController::class, 'registro'])->name('usuarios.registro');


Route::get('/login', [UserController::class, 'showLoginForm'])->name('usuarios.login');
Route::post('/login', [UserController::class, 'login'])->name('usuarios.login');

//rota para pagina interna
Route::get('/dashboard',function(){
    return view('usuarios.dashboard');
})->middleware('auth')->name('usuarios.dashboard');

Route::post('/logout', [UserController::class, 'logout']);

//rota para produtos
Route::resource('produtos', ProdutoController::class);

