<?php

use App\Http\Controllers\CarrinhoController;
use App\Http\Controllers\DashboardController;
use App\Http\Controllers\HomeController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UserController;
use App\Http\Controllers\CursoController;
use App\Http\Middleware\CursosMiddleware;


 Route::get('/', [HomeController::class, 'index']) ->name('home');


Route::get('/registro', [UserController::class, 'showRegistroForm'])->name('alunos.registro');
Route::post('/registro', [UserController::class, 'registro'])->name('alunos.registro');


Route::get('/login', [UserController::class, 'showLoginForm'])->name('alunos.login');
Route::post('/login', [UserController::class, 'login'])->name('alunos.login');

//rota para pagina interna
Route::get('/dashboard',[DashboardController::class,'index'])
->middleware('auth')->name('dashboard');

Route::post('/logout', [UserController::class, 'logout']);

//rota para produtos
Route::resource('cursos', CursoController::class)-> 
middleware(CursosMiddleware::class)->except('show');

Route::get('curso/{curso}', [CursoController::class,'show'])
->middleware('auth')->name('cursos.show');

Route::post('carrinho/add/{curso}', [CarrinhoController::class,'add'])
->middleware('auth')->name('carrinho.add');
