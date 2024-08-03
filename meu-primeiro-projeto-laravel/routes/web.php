<?php

use App\Http\Controllers\ProdutoController;
use App\Models\Produto;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view ('home');
});
Route::get('/products', [ProdutoController::class,'index'] ) ;

Route::get('/contacts', function () {
    return view ('contacts');
});
